import React, { Component } from 'react'
import TaskService from "./service/TaskService";
import LoginService from "./service/LoginService";
import { withRouter } from 'react-router-dom';


const ERROR_MESSAGE = "Error occured."
const ADD_SUCCESS_MESSAGE = "Task added Successfully"
const EDIT_SUCCESS_MESSAGE = "Task edited Successfully"

class TaskDetails extends Component {

    constructor(props) {
        super(props);
        this.state = {
            task: {
                id: this.props.match.params.id,
                name: '',
                description: '',
                status: "INPROGRESS",
            },
            isSuccess:false,
            isError: false,
            message: ""
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
        if (this.state.task.id == -1) {
            return
        }
        this.setState({isError: false, isSuccess:false, message: ""})

        TaskService.getTaskDetails(this.state.task.id)
            .then(response => response.ok ? response.json().then(response =>
                this.setState(prevState => ({
                task: {
                    ...prevState.task,
                    name: response.name,
                    description: response.description,
                    status: response.status
                },

            }))) :
            this.handleErrorResponse(response))
            .catch(error => this.setState({isError: true, message:ERROR_MESSAGE + error.message}))
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let task = {...this.state.task};
        task[name] = value;
        this.setState({task});
    }

    handleSubmit(event) {
        this.setState({isError: false, isSuccess:false, message: ""})
        const {task} = this.state;
        if (task.id == -1) {
            TaskService.addTask(task, LoginService.getLoggedInUserId())
                .then(response => response.ok ? this.setState({isSuccess: true, message: ADD_SUCCESS_MESSAGE})
                    : this.handleErrorResponse(response))
                .catch(() => this.handleErrorResponse())
        } else {
            TaskService.editTask(task)
                .then(response => response.ok ? this.setState({isSuccess: true, message: EDIT_SUCCESS_MESSAGE})
                    : this.handleErrorResponse(response))
                .catch(() => this.handleErrorResponse())

        }
    }

    handleErrorResponse = (response) => {
        response.json()
            .then(error => this.setState({isError: true, message:ERROR_MESSAGE + this.getErrorMessage(error)}))
            .catch(() => this.setState({isError: true, message:ERROR_MESSAGE}))

    }

    getErrorMessage = (error) => {
        return (
            Array.isArray(error) ?
            error[0].message : error.message)
    }

    onClose = () => {
        this.props.history.push(`/tasks`)
    }

    render() {
        const title = <h2  class="text-center">{this.state.task.id == -1 ? 'Add Task' : 'Edit Task'}</h2>;

        return<div class="container fullscreen">
                    {this.state.isError && <div className="alert alert-danger">{this.state.message}</div>}
                    {this.state.isSuccess && <div className="alert alert-success">{this.state.message}</div>}
                <div class ="row middle align-items-center">
                    <div class ="col"/>
                    <div class="col-md-4 col-md-offset-4">
                        {title}
                        <div class="form-group">
                            <label class="form-element-spacing">Task Name</label>
                            <input type="text" name="name" value={this.state.task.name}
                                       onChange={this.handleChange} class="form-control" placeholder="Name"/>
                        </div>
                        <div class="form-group">
                            <label class="form-element-spacing">Task Description</label>
                            <input type="text" name="description" value={this.state.task.description}
                                       onChange={this.handleChange} class="form-control" placeholder="Description"/>
                        </div>
                        <div class="text-center">
                            <button className="btn btn-success task-add-edit-button" onClick={this.handleSubmit}>Save</button>
                            <button className="btn btn-secondary task-cancel-button" onClick={this.onClose}>Close</button>
                        </div>
                    </div>
                    <div class ="col"/>
                </div>

            </div>
    }

}

export default withRouter(TaskDetails)