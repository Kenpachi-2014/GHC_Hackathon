import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import HomePage from './pages/HomePage';
import ReportPage from './pages/ReportPage';
import ExpenseForm from './components/ExpenseForm';
import ExpenseList from './components/ExpenseList';
import './styles/App.css';

const App: React.FC = () => {
    return (
        <Router>
            <div className="App">
                <h1>Expense Tracker</h1>
                <Switch>
                    <Route path="/" exact component={HomePage} />
                    <Route path="/reports" component={ReportPage} />
                    <Route path="/add-expense" component={ExpenseForm} />
                    <Route path="/expenses" component={ExpenseList} />
                </Switch>
            </div>
        </Router>
    );
};

export default App;