import { Link } from "react-router-dom";

export default function Navbar(){
    return <nav className="title">
        <a href="/" className="side_title">
            BMI record
            <ul>
                <li>
                    <Link to="/ViewRecord">ViewBMI RECORD</Link>
                </li>
            </ul>
        </a>
    </nav>
}