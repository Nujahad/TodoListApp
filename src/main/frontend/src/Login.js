import React, {Component} from 'react'
import LoginService from "./service/LoginService";

class LoginComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            userName: '',
            password: '',
            hasLoginFailed: false,
            showSuccessMessage: false,
        }

        this.handleChange = this.handleChange.bind(this)
        this.loginClicked = this.loginClicked.bind(this)
    }

    handleChange(event) {
        this.setState(
            {
                [event.target.name]: event.target.value
            }
        )
    }

    loginClicked() {
        LoginService
            .login({userName: this.state.userName, password: this.state.password})
            .then(response => {
                LoginService.registerSuccessfulLoginForJwt(response.userDetails.id, response.token)
                this.props.history.push(`/tasks`)
            }).catch(() => {
            this.setState({showSuccessMessage: false})
            this.setState({hasLoginFailed: true})
        })
    }

    render() {
        return (
            <div class="container fullscreen">
                <div class ="row middle align-items-center">
                    <div class ="col"/>
                    <div class="col-md-4 col-md-offset-4">
                        <h1 class="text-center">Login</h1>
                        {this.state.hasLoginFailed && <div className="alert alert-warning">Invalid Credentials</div>}
                        {this.state.showSuccessMessage && <div>Login Sucessful</div>}
                            <div class="form-group">
                                <label class="form-element-spacing">Username</label>
                                <input type="text"  class="form-control" name="userName" value={this.state.userName}
                                           onChange={this.handleChange} placeholder="Username"/>
                            </div>
                            <div class="form-group">
                                <label class="form-element-spacing">Password</label>
                                <input type="password" class="form-control" name="password" value={this.state.password}
                                           onChange={this.handleChange} placeholder="Password"/>
                            </div>
                            <div class="text-center">
                                <button className="btn btn-success" onClick={this.loginClicked}>Login</button>
                            </div>
                    </div>
                    <div class ="col"/>
                </div>
            </div>
        )
    }
}

export default LoginComponent