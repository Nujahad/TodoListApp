import LoginService from "../service/LoginService";
import {TASK_API_WITH_USER_ID} from "../service/TaskService";

class RestHelper {

    fetch = (url, method, content) => {
        var error = false;
        return(
            fetch(url, {
                method: method,
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": LoginService.createJWTToken(),
                },
                body: JSON.stringify(content)
            }).then((response => response.ok ?  response.json() : this.handleError(response)))
        )
    }

    fetchWithoutAuthentication = (url, method, content) => {
        return (
            fetch(url, {
                method: method,
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": LoginService.createJWTToken(),
                },
                body: JSON.stringify(content)
            }).then((response => response.ok ? response : this.handleError(response)))
        )
    }


    handleError = (response) => {
        if(response.status === 401) {
            LoginService.logout()
        }
        response.json().then(error => Promise.reject(error))
    }

} export default new RestHelper()