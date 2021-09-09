import LoginService from "./LoginService";

const TASK_API = `/api/tasks/`
export const STATUS = {
    COMPLETED : "COMPLETED",
    INPROGRESS : "INPROGRESS"
}
class TaskService{
        fetchTodoList = () => {
            return(
        fetch(TASK_API, {
            headers: {
                "Content-Type": "application/json",
                "Authorization": LoginService.createJWTToken(),
            }
        }))
    }

    deleteTodoList = (id) => {
        return(
            fetch(TASK_API+id, {
            method : "DELETE",
            headers: {
                "Content-Type": "application/json",
                "Authorization": LoginService.createJWTToken(),
            }
        })
        )
    }

    addTask = (task) => {
            return(
                fetch(TASK_API, {
                    method : "POST",
                    headers: {
                        "Content-Type": "application/json",
                        "Authorization": LoginService.createJWTToken(),
                    },
                    body : JSON.stringify(task)
                })
            )

    }

    editTask = async (task) => {
        return (
            await fetch(TASK_API, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": LoginService.createJWTToken(),
                },
                body: JSON.stringify(task)
            })
        )

    }

    getTaskDetails = (id) => {
        return(
            fetch(TASK_API+id, {
                method : "GET",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": LoginService.createJWTToken(),
                }
            })

        )
    }

    updateStatus = async (id, status) => {
        return (
            await fetch(TASK_API+id, {
                method: "PATCH",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": LoginService.createJWTToken(),
                },
                body: JSON.stringify(status)
            })
        )

    }
}
export default new TaskService();