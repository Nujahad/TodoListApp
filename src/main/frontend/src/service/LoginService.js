const LOGIN_API_URL = `api/users/signin`

class LoginService {
    login = (login) => {
        return (fetch(LOGIN_API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(login),
        }).then(response => response.json()))
    }

    createJWTToken() {
        return 'Bearer ' + sessionStorage.getItem("TOKEN")
    }

    registerSuccessfulLoginForJwt(userId, token) {
        sessionStorage.setItem("USER_ID_SESSION_ATTRIBUTE_NAME", userId)
        sessionStorage.setItem("TOKEN", token)
    }
    logout() {
        sessionStorage.removeItem("USER_ID_SESSION_ATTRIBUTE_NAME");
        sessionStorage.removeItem("TOKEN");

    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem("USER_ID_SESSION_ATTRIBUTE_NAME")
        if (user === null) return false
        return true
    }

    getLoggedInUserId() {
        let user = sessionStorage.getItem("USER_ID_SESSION_ATTRIBUTE_NAME")
        if (user === null) return ''
        return user
    }
}

export default new LoginService()