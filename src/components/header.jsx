import React from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";

const Header = () => {
  const location = useLocation();
  const navigate = useNavigate();

  const handleOurTravelClick = () => {
    navigate("/ourtravel");
  };

  return (
    <header>
      <div className="header">
        <div className="container">
          <div className="row">
            <div className="col-xl-3 col-lg-3 col-md-3 col-sm-3 col logo_section">
              <div className="full">
                <div className="center-desk">
                  <div className="logo">
                    <a href="/">
                      <img
                        src={require("../images/files.jpg")}
                        style={{ width: "150px", height: "71px" }}
                        alt="#"
                      />
                    </a>
                  </div>
                </div>
              </div>
            </div>
            <div className="col-xl-9 col-lg-9 col-md-9 col-sm-9">
              <nav className="navigation navbar navbar-expand-md navbar-dark ">
                <button
                  className="navbar-toggler"
                  type="button"
                  data-toggle="collapse"
                  data-target="#navbarsExample04"
                  aria-controls="navbarsExample04"
                  aria-expanded="false"
                  aria-label="Toggle navigation"
                >
                  <span className="navbar-toggler-icon" />
                </button>
                <div className="collapse navbar-collapse" id="navbarsExample04">
                  <ul className="navbar-nav mr-auto">
                    <li className="nav-item">
                      <Link
                        className={`nav-link ${
                          location.pathname === "/home" ? "active" : ""
                        }`}
                        to="/"
                      >
                        Home
                      </Link>
                    </li>
                    <li className="nav-item">
                      <Link
                        className={`nav-link ${
                          location.pathname === "/about" ? "active" : ""
                        }`}
                        to="/about"
                      >
                        About
                      </Link>
                    </li>
                    <li className="nav-item">
                      <Link
                        className={`nav-link ${
                          location.pathname === "/ourtravel" ? "active" : ""
                        }`}
                        to="/ourtravel"
                        onClick={handleOurTravelClick}
                      >
                        Our Travel
                      </Link>
                    </li>
                    <li className="nav-item">
                      <Link
                        className={`nav-link ${
                          location.pathname === "/login" ? "active" : ""
                        }`}
                        to="/login"
                      >
                        Login
                      </Link>
                    </li>
                    <li className="nav-item">
                      <Link
                        className={`nav-link ${
                          location.pathname === "/sign in" ? "active" : ""
                        }`}
                        to="/sign in"
                      >
                        Sign In
                      </Link>
                    </li>
                  </ul>
                </div>
              </nav>
            </div>
          </div>
        </div>
      </div>
    </header>
  );
};

export default Header;
