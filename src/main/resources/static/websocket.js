const url = location.host === "gh-mj-ws.herokuapp.com" ? "wss://gh-mj-ws.herokuapp.com/chat" : "ws://localhost:8080/chat";
const ws = Stomp.client(url);

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
            if(content.endsWith("cleared the chat!")) chatContainer.innerHTML = "";
            chatContainer.innerHTML += "<p class='message'>" + "<span class='nick'>" + username + "</span>" + " - " + content + "</p>";
        })
        chatContainer.scrollTop = chatContainer.scrollHeight;
    })

    const handleSendButton = () => {
        const message = messageInput.value;
        if(message.length === 0) return;
        ws.send("/app/chat", {}, JSON.stringify({'username': username, 'content': message}));
        messageInput.value = "";
    };

    sendButton.addEventListener("click", handleSendButton);
    messageInput.addEventListener("keypress", ({keyCode}) => { if(keyCode === 13) handleSendButton() });

    loginContainer.style.display = 'none';
    messageContainer.style.display = '';

    messageInput.focus();
})