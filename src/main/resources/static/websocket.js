const ws = Stomp.client("ws://localhost:8080/chat");

let username = "Guest";
const chatContainer = document.getElementById("chat-box");
const loginContainer = document.getElementById("login-box");
const messageContainer = document.getElementById("message-box")
const usernameInput = document.getElementById("username");
const messageInput = document.getElementById("message");
const loginButton = document.getElementById("login");
const sendButton = document.getElementById("send");

ws.connect();

loginButton.addEventListener("click", () => {
    username = usernameInput.value.length === 0 ? username : usernameInput.value;

    ws.send("/app/chat", {}, JSON.stringify({'username': username, 'content': '[joined]'}));

    ws.subscribe("/topic/messages", ({body}) => {
        const messages = JSON.parse(body);
        messages.map(({username, content}) => {
            chatContainer.innerHTML += username + " - " + content + "<br \>";
        })
    })

    sendButton.addEventListener("click", () => {
        const message = messageInput.value;
        ws.send("/app/chat", {}, JSON.stringify({'username': username, 'content': message}));
    })

    loginContainer.style.display = 'none';
    messageContainer.style.display = '';
})