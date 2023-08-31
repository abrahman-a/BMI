import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Navbar from './Navbar';
import ViewRecord from './ViewRecord';

function App() {
  return (
    <Router>
          <div className="App">
          <Navbar/>
             <div className="Content">
             <Switch>
              <Route exact path="/ViewRecord">
                  <ViewRecord/>
              </Route>
             </Switch>
             </div> 
          </div>
    </Router>
  );
}

export default App;
