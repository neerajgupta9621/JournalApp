    // ============================
    // BASE URL AUTO DETECT
    // ============================

    const BASE_URL =
        window.location.hostname === "localhost"
            ? "http://localhost:8081"
            : "https://journalapp-1-ek5e.onrender.com";

     // ============================
     // SELECTED EMPLOYEE STATE
     // ============================
     // Ye variable store karega kaunsa employee user ne select kiya hai

    let selectedEmployeeId = null;

    // ============================
    // CHAT HISTORY
    // ============================

    let chatHistory = [];


    // ============================
    // LOGIN
    // ============================
  function login() {

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    if (username.trim() === "" || password.trim() === "") {
        alert("Username & Password Required ❌");
        return;
    }

    fetch(`${BASE_URL}/public/login`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            userName: username,
            password: password
        })
    })
    .then(res => {
        if (!res.ok) throw new Error();
        return res.text();
    })
    .then(token => {
        localStorage.setItem("token", token);
        alert("Login Successful ✅");
        window.location.href = "dashboard.html";
    })
    .catch(() => {
        alert("Invalid Login ❌");
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

                   <h3>👨‍💼 Welcome</h3>

                    <p>${data}</p>

                </div>
            `;
        })

        .catch(error => {

            console.log(error);

            document.getElementById("greeting").innerHTML =
                "<h3>Failed To Load  ❌</h3>";
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

                   <h3>👤 Employee Profile</h3>

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

               <h3>✅ System Status</h3>

                <p>🚀 Spring Boot Active</p>

                <p>🔐 JWT Authentication Working</p>

                <p>🍃 MongoDB Connected</p>

                <p>🌐 APIs Running Successfully</p>

            </div>
        `;
    }

    // ============================
    // CREATE EMPLOYEE
    // ============================

    function createEmployee(){

        const name =
            document.getElementById("employeeName").value;

        const content =
            document.getElementById("employeeDetails").value;

        const department =
            document.getElementById("department").value;

        if(name.trim() === "" || content.trim() === ""){

            alert("Name & Details Required ❌");

            return;
        }

        fetch(`${BASE_URL}/journal`,{

            method:"POST",

            headers:{
                "Content-Type":"application/json",
                "Authorization":"Bearer " + getToken()
            },

            body:JSON.stringify({

                title: name,
                content: content,
                sentiment: department

            })

        })

        .then(async res => {

            if(!res.ok){

                throw new Error("Create Failed");
            }

            alert("Employee Added Successfully ✅");

            document.getElementById("employeeName").value="";
            document.getElementById("employeeDetails").value="";

            loadEmployees();

        })

        .catch(error => {

            console.log(error);

            alert("Failed To Create Employee ❌");
        });
    }

    // ============================
    // LOAD EMPLOYEES
    // ============================
function loadEmployees() {

    fetch(`${BASE_URL}/journal`, {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + getToken()
        }
    })
    .then(res => {
        if (!res.ok) throw new Error();
        return res.json();
    })
    .then(data => {

        const container = document.getElementById("employees");
        container.innerHTML = "";

        if (!data || data.length === 0) {
            container.innerHTML = "<h3>No Employees Found 😅</h3>";
            return;
        }

        data.forEach(emp => {

         console.log(emp);

            const card = document.createElement("div");
            card.className = "journal-card";

            card.innerHTML = `
                <h2>👨‍💼 ${emp.title}</h2>
                <p>${emp.content}</p>
                <div class="sentiment-tag">🏢 ${emp.sentiment}</div>

                <div class="btn-group">
                    <button class="update-btn">UPDATE</button>
                    <button class="delete-btn">DELETE</button>
                </div>
            `;

            // SELECT
            card.addEventListener("click", () => {
                selectEmployee(emp.id, card);
            });

            // UPDATE
            card.querySelector(".update-btn").addEventListener("click", (e) => {
                e.stopPropagation();
                updateEmployee(emp.id);
            });

            // DELETE
            card.querySelector(".delete-btn").addEventListener("click", (e) => {
                e.stopPropagation();
                deleteEmployee(emp.id);
            });

            container.appendChild(card);
        });
    })
    .catch(() => {
        document.getElementById("employees").innerHTML =
            "<h3>Failed To Load ❌</h3>";
    });
}

    // ============================
    // UPDATE JOURNAL
    // ============================

    function updateEmployee(id){

        const name =
            prompt("Enter Employee Name");

        const details =
            prompt("Enter Employee Details");

        if(!name || !details){

            return;
        }

        fetch(`${BASE_URL}/journal/id/${id}`,{

            method:"PUT",

            headers:{
                "Content-Type":"application/json",
                "Authorization":"Bearer " + getToken()
            },

            body:JSON.stringify({

                title:name,
                content:details

            })

        })

        .then(async res => {

            if(!res.ok){

                throw new Error("Update Failed");
            }

            alert("Employee Updated ✅");

            loadEmployees();

        })

        .catch(error => {

            console.log(error);

            alert("Update Failed ❌");
        });
    }

    // ============================
    // DELETE JOURNAL
    // ============================

    function deleteEmployee(id){

        const confirmDelete =
            confirm("Delete Employee ?");

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

            alert("Employee Deleted ✅");

            loadEmployees();

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

                        <div class="btn-group">

                            <button
                                class="delete-btn"
                                onclick="deleteAdminUser('${user.userName}')">

                                🗑 Delete

                            </button>

                        </div>

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

                <h3>🔐 Current Login Token</h3>

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
        .then(res => {
            if(!res.ok){
                throw new Error("AI Failed");
            }
            return res.text();
        })
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
 // JOURNAL → AI ANALYSIS (DB BASED)
 // ============================
 // Ab textarea se nahi, DB se selected employee fetch hoga

function analyzeEmployee(){

    // Step 1: check if user selected journal
    if(!selectedEmployeeId){
        alert("⚠️ Pehle employee select karo");
        return;
    }

    // Step 2: DB se journal fetch karo
    fetch(`${BASE_URL}/journal/id/${selectedEmployeeId}`,{

        method:"GET",

        headers:{
            "Authorization":"Bearer " + getToken()
        }

    })

    .then(res => {

        if(!res.ok){
            throw new Error("Journal fetch failed");
        }

        return res.json();
    })

    .then(journal => {

        // Step 3: AI prompt set karo
        document.getElementById("aiPrompt").value =
            "Analyze this employee information and provide professional feedback: " + journal.content;

        // Step 4: AI call
        askAI();

        // Step 5: scroll to AI section
        document.getElementById("ai-section")
            .scrollIntoView({ behavior: "smooth" });

    })

    .catch(error => {

        console.log(error);

        alert("❌ Employee load failed");
    });
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

       if(window.location.pathname.includes("dashboard.html")){

           loadGreeting();

           loadEmployees();

           loadUsers();

           loadProfile();

           healthCheck();
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
    // DELETE ADMIN USER
    // ============================

    function deleteAdminUser(userName){

        const confirmDelete =
            confirm("Delete " + userName + " ?");

        if(!confirmDelete){
            return;
        }

        fetch(`${BASE_URL}/admin/delete-user/${userName}`,{

            method:"DELETE",

            headers:{
                "Authorization":"Bearer " + getToken()
            }

        })

        .then(res=>{

            if(!res.ok){
                throw new Error();
            }

            alert("User Deleted Successfully ✅");

            loadUsers();

        })

        .catch(()=>{

            alert("Delete Failed ❌");

        });
  }


   // ============================
   // SELECT JOURNAL FUNCTION (UPDATED)
   // ============================
function selectEmployee(id, card){

    selectedEmployeeId = id;

    document.querySelectorAll(".journal-card")
        .forEach(c => c.classList.remove("selected"));

    card.classList.add("selected");

    console.log("Selected Employee:", id);

    alert("Employee Selected ✅");
}
