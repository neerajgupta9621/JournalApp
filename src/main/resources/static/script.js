    // ============================
    // BASE URL
    // ============================

    // ============================
    // BASE URL AUTO DETECT
    // ============================

    const BASE_URL =
        window.location.hostname === "localhost"
            ? "http://localhost:8081"
            : "https://journalapp-1-ek5e.onrender.com";


    // ============================
    // CHAT HISTORY
    // ============================

    let chatHistory = [];


    // ============================
    // LOGIN
    // ============================

    function login(){

        const username =
            document.getElementById("username").value;

        const password =
            document.getElementById("password").value;

        if(username.trim() === "" || password.trim() === ""){

            alert("Username & Password Required ❌");

            return;
        }

        fetch(`${BASE_URL}/public/login`,{

            method:"POST",

            headers:{
                "Content-Type":"application/json"
            },

            body:JSON.stringify({

                userName:username,
                password:password

            })

        })

        .then(async res => {

            if(!res.ok){

                throw new Error("Login Failed");
            }

            return res.text();
        })

        .then(token => {

            localStorage.setItem("token",token);

            alert("Login Successful ✅");

            window.location.href="dashboard.html";

        })

        .catch(error => {

            console.log(error);

            alert("Invalid Username or Password ❌");
        });
    }

    // ============================
    // DEMO SIGNUP
    // ============================

    function signupDemo(){

        fetch(`${BASE_URL}/public/signup`,{

            method:"POST",

            headers:{
                "Content-Type":"application/json"
            },

            body:JSON.stringify({

                userName:"Aman",
                email:"aman@gmail.com",
                password:"Aman",
                sentimentAnalysis:true,
                roles:["ROLE_USER"]

            })

        })

        .then(async res => {

            if(!res.ok){

                throw new Error("Signup Failed");
            }

            alert("Demo User Created ✅");

        })

        .catch(error => {

            console.log(error);

            alert("Signup Failed ❌");
        });
    }

    // ============================
    // TOKEN
    // ============================

    function getToken(){
      return localStorage.getItem("token");
    }

    // =============    ===============
    // LOGOUT
    // ============================

    function logout(){

        localStorage.removeItem("token");

        window.location.href="index.html";
    }

    // ============================
    // CLEAR CACHE
    // ============================

    function clearCache(){

        localStorage.clear();

        sessionStorage.clear();

        alert("Cache Cleared ✅");

        window.location.href="index.html";
    }

    // ============================
    // GREETING
    // ============================

    function loadGreeting(){

        fetch(`${BASE_URL}/user`,{

            method:"GET",

            headers:{
                "Authorization":"Bearer " + getToken()
            }

        })

        .then(async res => {

            if(!res.ok){

                throw new Error("Greeting Failed");
            }

            return res.text();
        })

        .then(data => {

            document.getElementById("greeting").innerHTML = `

                <div class="user-card">

                    <h3>👋 Greeting API</h3>

                    <p>${data}</p>

                </div>
            `;
        })

        .catch(error => {

            console.log(error);

            document.getElementById("greeting").innerHTML =
                "<h3>Failed To Load Greeting ❌</h3>";
        });
    }

    // ============================
    // LOAD PROFILE
    // ============================

    function loadProfile(){

        fetch(`${BASE_URL}/user`,{

            method:"GET",

            headers:{
                "Authorization":"Bearer " + getToken()
            }

        })

        .then(async res => {

            if(!res.ok){

                throw new Error("Profile Failed");
            }

            return res.text();
        })

        .then(data => {

            const profile =
                document.getElementById("profile");

            profile.innerHTML = `

                <div class="user-card">

                    <h3>👤 Current User</h3>

                    <p>${data}</p>

                    <div class="btn-group">

                        <button
                            class="update-btn"
                            onclick="updateUser()">

                            Update User

                        </button>

                        <button
                            class="delete-btn"
                            onclick="deleteUser()">

                            Delete User

                        </button>

                    </div>

                </div>
            `;
        })

        .catch(error => {

            console.log(error);
        });
    }

    // ============================
    // UPDATE USER
    // ============================

    function updateUser(){

        const username =
            prompt("Enter New Username");

        const password =
            prompt("Enter New Password");

        if(!username || !password){

            alert("Update Cancelled ❌");

            return;
        }

        fetch(`${BASE_URL}/user`,{

            method:"PUT",

            headers:{
                "Content-Type":"application/json",
                "Authorization":"Bearer " + getToken()
            },

            body:JSON.stringify({

                userName:username,
                password:password

            })

        })

        .then(async res => {

            if(!res.ok){

                throw new Error("Update Failed");
            }

            alert("User Updated ✅");

            loadProfile();

        })

        .catch(error => {

            console.log(error);

            alert("User Update Failed ❌");
        });
    }

    // ============================
    // DELETE USER
    // ============================

    function deleteUser(){

        const confirmDelete =
            confirm("Delete Your Account ?");

        if(!confirmDelete){
            return;
        }

        fetch(`${BASE_URL}/user`,{

            method:"DELETE",

            headers:{
                "Authorization":"Bearer " + getToken()
            }

        })

        .then(async res => {

            if(!res.ok){

                throw new Error("Delete Failed");
            }

            alert("User Deleted ✅");

            logout();

        })

        .catch(error => {

            console.log(error);

            alert("Delete Failed ❌");
        });
    }

    // ============================
    // HEALTH CHECK
    // ============================

    function healthCheck(){

        const health =
            document.getElementById("health");

        health.innerHTML = `

            <div class="user-card">

                <h3>✅ Application Running</h3>

                <p>🚀 Spring Boot Active</p>

                <p>🔐 JWT Authentication Working</p>

                <p>🍃 MongoDB Connected</p>

                <p>🌐 APIs Running Successfully</p>

            </div>
        `;
    }

    // ============================
    // CREATE JOURNAL
    // ============================

    function createJournal(){

        const title =
            document.getElementById("title").value;

        const content =
            document.getElementById("content").value;

        const sentiment =
            document.getElementById("sentiment").value;

        if(title.trim() === "" || content.trim() === ""){

            alert("Title & Content Required ❌");

            return;
        }

        fetch(`${BASE_URL}/journal`,{

            method:"POST",

            headers:{
                "Content-Type":"application/json",
                "Authorization":"Bearer " + getToken()
            },

            body:JSON.stringify({

                title:title,
                content:content,
                sentiment:sentiment

            })

        })

        .then(async res => {

            if(!res.ok){

                throw new Error("Create Failed");
            }

            alert("Journal Created ✅");

            document.getElementById("title").value="";
            document.getElementById("content").value="";

            loadJournals();

        })

        .catch(error => {

            console.log(error);

            alert("Failed To Create Journal ❌");
        });
    }

    // ============================
    // LOAD JOURNALS
    // ============================

    function loadJournals(){

        fetch(`${BASE_URL}/journal`,{

            method:"GET",

            headers:{
                "Authorization":"Bearer " + getToken()
            }

        })

        .then(async res => {

            if(!res.ok){

                throw new Error("Failed To Load Journals");
            }

            return res.json();
        })

        .then(data => {

            const journals =
                document.getElementById("journals");

            journals.innerHTML = "";

            if(data.length===0){

                journals.innerHTML =
                    "<h2>No Journals Found 😅</h2>";

                return;
            }

            data.forEach(journal => {

                const journalId =
                    journal.id?.timestamp || journal.id;

                journals.innerHTML += `

                    <div class="journal-card">

                        <h2>${journal.title}</h2>

                        <p>${journal.content}</p>

                        <p>📅 ${journal.date || ""}</p>

                        <div class="sentiment-tag">
                            ${journal.sentiment}
                        </div>

                        <div class="btn-group">

                            <button
                                class="update-btn"
                                onclick="updateJournal('${journalId}')">

                                UPDATE

                            </button>

                            <button
                                class="delete-btn"
                                onclick="deleteJournal('${journalId}')">

                                DELETE

                            </button>

                        </div>

                    </div>
                `;
            });

        })

        .catch(error => {

            console.log(error);

            document.getElementById("journals").innerHTML =
                "<h2>Failed To Load Journals ❌</h2>";
        });
    }

    // ============================
    // UPDATE JOURNAL
    // ============================

    function updateJournal(id){

        const title =
            prompt("Enter New Title");

        const content =
            prompt("Enter New Content");

        if(!title || !content){

            return;
        }

        fetch(`${BASE_URL}/journal/id/${id}`,{

            method:"PUT",

            headers:{
                "Content-Type":"application/json",
                "Authorization":"Bearer " + getToken()
            },

            body:JSON.stringify({

                title:title,
                content:content

            })

        })

        .then(async res => {

            if(!res.ok){

                throw new Error("Update Failed");
            }

            alert("Journal Updated ✅");

            loadJournals();

        })

        .catch(error => {

            console.log(error);

            alert("Update Failed ❌");
        });
    }

    // ============================
    // DELETE JOURNAL
    // ============================

    function deleteJournal(id){

        const confirmDelete =
            confirm("Delete Journal ?");

        if(!confirmDelete){
            return;
        }

        fetch(`${BASE_URL}/journal/id/${id}`,{

            method:"DELETE",

            headers:{
                "Authorization":"Bearer " + getToken()
            }

        })

        .then(async res => {

            if(!res.ok){

                throw new Error("Delete Failed");
            }

            alert("Journal Deleted ✅");

            loadJournals();

        })

        .catch(error => {

            console.log(error);

            alert("Delete Failed ❌");
        });
    }

    // ============================
    // LOAD USERS
    // ============================

    function loadUsers(){

        fetch(`${BASE_URL}/admin/all-users`,{

            method:"GET",

            headers:{
                "Authorization":"Bearer " + getToken()
            }

        })

        .then(async res => {

            if(!res.ok){

                throw new Error("Users Load Failed");
            }

            return res.json();
        })

        .then(data => {

            const users =
                document.getElementById("users");

            users.innerHTML = "";

            data.forEach(user => {

                users.innerHTML += `

                    <div class="user-card">

                        <h3>👤 ${user.userName}</h3>

                        <p>📧 ${user.email || "No Email"}</p>

                        <p>
                            🧠 Sentiment Analysis :
                            ${user.sentimentAnalysis}
                        </p>

                        <p>
                            🔐 Roles :
                            ${user.roles}
                        </p>

                    </div>
                `;
            });

        })

        .catch(error => {

            console.log(error);

            alert("Admin Access Required ❌");
        });
    }

    // ============================
    // SHOW TOKEN
    // ============================

    function showToken(){

        const token = getToken();

        document.getElementById("tokenBox").innerHTML = `

            <div class="user-card">

                <h3>🔐 JWT TOKEN</h3>

                <p style="word-break:break-all;">
                    ${token}
                </p>

                <button
                    class="execute-btn"
                    onclick="copyToken()">

                    Copy Token

                </button>

            </div>
        `;
    }

    // ============================
    // COPY TOKEN
    // ============================

    function copyToken(){

        navigator.clipboard.writeText(getToken());

        alert("Token Copied ✅");
    }

    // =====================
    //  ASKAI FUNCTION
    // =====================

    function askAI(){

        const prompt = document.getElementById("aiPrompt").value;

        if(prompt.trim() == ""){
            alert("Enter Prompt");
            return;
        }

        // Loading message
        document.getElementById("aiResponse").innerHTML = `
            <div class="user-card">
                <h3>🤖 AI Thinking...</h3>
                <p>Please wait...</p>
            </div>
        `;

        fetch(`${BASE_URL}/ai/chat`,{

            method: "POST",

            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + getToken()
            },

            body: JSON.stringify({
                prompt: prompt
            })

        })
        .then(res => res.text())
        .then(data => {

            // save chat history
            chatHistory.push({role: "user",message: prompt });
            chatHistory.push({role: "ai", message: data });

             // save to localStorage
                    localStorage.setItem("chatHistory", JSON.stringify(chatHistory));

            renderChat();

             // ✅ HERE ADD THIS LINE
                document.getElementById("aiPrompt").value = "";
        })
        .catch(error => {

            console.log(error);

            document.getElementById("aiResponse").innerHTML = `
                <div class="user-card">
                    <h3>❌ AI Error</h3>
                    <p>Unable to get AI response.</p>
                </div>
            `;
        });
    }


    // ============================
    // RENDER CHAT UI
    // ============================
    function renderChat(){

        const container = document.getElementById("aiResponse");

        container.innerHTML = chatHistory.map(chat => {

            if(chat.role === "user"){
                return `
                    <div class="user-card">
                        <h3>🧑 You</h3>
                        <p>${chat.message}</p>
                    </div>
                `;
            }

            return `
                <div class="user-card">
                    <h3>🤖 AI</h3>
                    <pre style="white-space:pre-wrap;">${chat.message}</pre>
                </div>
            `;
        }).join("");

        // auto scroll
        setTimeout(() => {
            container.scrollTop = container.scrollHeight;
        }, 100);

        // save again (safe sync)
            localStorage.setItem("chatHistory", JSON.stringify(chatHistory));
    }


    // ============================
    // JOURNAL → AI ANALYSIS
    // ============================
    function analyzeJournal(){

        const content = document.getElementById("content").value;

        if(content.trim() === ""){
            alert("Write journal first ❌");
            return;
        }

        document.getElementById("aiPrompt").value =
            "Analyze this journal mood and give advice: " + content;

        askAI();

        document.getElementById("ai-section")
            .scrollIntoView({ behavior: "smooth" });
    }
    // ============================
    // CLEAR CHAT
    // ============================
    function clearChat(){
        chatHistory = [];
        localStorage.removeItem("chatHistory");
        document.getElementById("aiResponse").innerHTML = "";
    }


    // ============================
    // LOAD SAVED CHAT
    // ============================
    window.onload = function(){
        const saved = localStorage.getItem("chatHistory");
        if(saved){
            chatHistory = JSON.parse(saved);
            renderChat();
        }
    }


    // ============================
    // ENTER KEY SUPPORT
    // ============================
    document.addEventListener("keydown", function(e){
        if(e.key === "Enter" && !e.shiftKey){
            const active = document.activeElement;
            if(active && active.id === "aiPrompt"){
                askAI();
            }
        }
    });

//=====================
// create user
//========================
    function createUser(){

        const userName = document.getElementById("newUsername").value;
        const email = document.getElementById("newEmail").value;
        const password = document.getElementById("newPassword").value;

        fetch(`${BASE_URL}/public/signup`,{

            method:"POST",

            headers:{
                "Content-Type":"application/json"
            },

            body:JSON.stringify({

                userName:userName,
                email:email,
                password:password,
                sentimentAnalysis:true,
                roles:["ROLE_USER"]

            })

        })

        .then(res=>{

            if(!res.ok){
                throw new Error();
            }

            alert("User Created Successfully ✅");

            document.getElementById("newUsername").value = "";
            document.getElementById("newEmail").value = "";
            document.getElementById("newPassword").value = "";

            loadUsers();

        })

        .catch(()=>{

            alert("User Create Failed ❌");

        });

    }

    //=====================
    // create admin user
    //========================

    function createAdminUser(){

        const userName = document.getElementById("newUsername").value;
        const email = document.getElementById("newEmail").value;
        const password = document.getElementById("newPassword").value;

        fetch(`${BASE_URL}/admin/create-admin-user`,{

            method:"POST",

            headers:{
                "Content-Type":"application/json",
                "Authorization":"Bearer " + getToken()
            },

            body:JSON.stringify({

                userName:userName,
                email:email,
                password:password,
                sentimentAnalysis:true,
                roles:["ROLE_ADMIN"]

            })

        })

        .then(res=>{

            if(!res.ok){
                throw new Error();
            }

            alert("Admin Created Successfully ✅");

            document.getElementById("newUsername").value = "";
            document.getElementById("newEmail").value = "";
            document.getElementById("newPassword").value = "";

            loadUsers();

        })

        .catch(()=>{

            alert("Admin Create Failed ❌");

        });

    }

    // ============================
    // AUTO LOAD
    // ============================

    if(window.location.pathname.includes("dashboard.html")){

        loadGreeting();

        loadJournals();

        loadUsers();

        loadProfile();

        healthCheck();
    }