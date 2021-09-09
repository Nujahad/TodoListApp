import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './App.css';
import TaskDetails from "./TaskDetails";
import TaskList from "./TaskList";
import Login from "./Login";
import AuthenticatedRoute from "./AuthenticatedRoute";
import MenuComponent from "./MenuComponent";

function App() {
    const api_regex = /^\/api\/.*/
    // if using "/api/" in the pathname, don't use React Router
    if (api_regex.test(window.location.pathname)) {
        return <div /> // must return at least an empty div
    } else {
        return (
            <Router>
                <MenuComponent/>
                <Switch>
                    <Route path='/' exact={true} component={Login}/>
                    <Route path='/Login' exact={true} component={Login}/>
                    <AuthenticatedRoute path='/tasks' exact={true} component={TaskList}/>
                    <AuthenticatedRoute path="/tasks/:id" component={TaskDetails}/>
                </Switch>
            </Router>
        );
    }
}

export default App;
