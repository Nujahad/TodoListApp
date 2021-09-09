import React, { Component } from 'react'
import { Link, withRouter } from 'react-router-dom'
import AuthenticationService from './service/LoginService';
import LoginService from "./service/LoginService";

class MenuComponent extends Component {

    render() {
        const isUserLoggedIn = LoginService.isUserLoggedIn();

        return (
            <header>
                <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                    <ul class="navbar-nav nav-bar-left-margin">
                        <li><Link className="nav-link" to="/tasks">Todo List</Link></li>
                    </ul>
                    <ul className="navbar-nav navbar-collapse justify-content-end nav-bar-right-margin">
                        {!isUserLoggedIn && <li><Link className="nav-link" to="/login">Login</Link></li>}
                        {isUserLoggedIn && <li><Link className="nav-link" to="/login" onClick={AuthenticationService.logout}>Logout</Link></li>}
                    </ul>
                </nav>
            </header>
        )
    }
}

export default withRouter(MenuComponent)