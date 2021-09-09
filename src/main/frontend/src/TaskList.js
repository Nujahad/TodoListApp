import React, {Component} from "react";
import LoginService from "./service/LoginService";
import TaskService, {STATUS} from "./service/TaskService";


class TaskList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            tasks: [],
            isLoading: true,
            userId: "",
            message: ""
        };
    }

    componentDidMount() {
        this.setState({isLoading: true});
        this.fetchTodoList();
    }

    fetchTodoList = () => {
        this.setState({isLoading: true});
        TaskService.fetchTodoList(LoginService.getLoggedInUserId())
        .then(response => response.ok ? response.json()
            .then(response => this.setState({tasks: response, isLoading: false}))
        : this.setState({isLoading: false}));
    }

    deleteTask = (id) => {
        this.setState({isLoading: true});
        TaskService.deleteTodoList(id)
            .then( response=> response.ok ? this.updateList(id) :
                this.setState({isError: true, message:"Error occured while deleting"}));
    }

    updateList = (id) => {
            let updatedTasks = [...this.state.tasks].filter(i => i.id !== id);
            this.setState({tasks: updatedTasks, isLoading: false});
    }

    updateTask = (id) => {
        this.props.history.push(`/tasks/${id}`)
    }

    addTask = () =>{
        this.props.history.push(`/tasks/-1`)
    }

    convertUTCDateToLocalDate = (date) =>{
        const newDate = new Date(date.getTime() + date.getTimezoneOffset() * 60 * 1000);
        const offset = date.getTimezoneOffset() / 60;
        const hours = date.getHours();
        newDate.setHours(hours - offset);
        return newDate;
    }

    updateStatus = (id, status) => {
        this.setState({isLoading: true});
        var updatedStatus = status === STATUS.COMPLETED ? STATUS.INPROGRESS : STATUS.COMPLETED
        TaskService.updateStatus(id, updatedStatus)
            .then(response => response.ok ? this.fetchTodoList() :
            this.setState({isError: true, message:"Error occured while updating status"}))
    }

    render() {
        return (
            <div className="container">
                {this.state.isLoading && (<div>Request in Progress.....</div>)}
                <div className="container">
                    <h3 class="text-center task-list-top-margin">Todo List</h3>
                    <table className="table">
                        <thead>
                        <tr>
                            <th>Status</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Last Modified</th>
                            <th/>
                            <th/>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.tasks.map(task =>
                                <tr key={task.id}>
                                    <td>
                                        <input type="checkbox" checked={task.status === STATUS.COMPLETED} onChange={() =>this.updateStatus(task.id, task.status)}/>
                                    </td>
                                    <td>{task.name}</td>
                                    <td>{task.description}</td>
                                    <td>{this.convertUTCDateToLocalDate(new Date(task.lastModified)).toLocaleString()}</td>
                                    <td>
                                        <button className="btn btn-warning" onClick={() => this.updateTask(task.id)}>Edit</button>
                                    </td>
                                    <td>
                                        <button className="btn btn-danger"
                                                onClick={() => this.deleteTask(task.id)}>Delete
                                        </button>
                                    </td>
                                </tr>
                            )
                        }
                        </tbody>
                    </table>
                    <div class="text-center task-list-top-margin">
                        <button className="btn btn-success" onClick={this.addTask}>Add</button>
                    </div>
                </div>
            </div>
        )
    }
}

export default TaskList