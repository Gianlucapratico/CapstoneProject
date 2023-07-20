import React, { useEffect, useState } from "react";
import $ from "jquery";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { Navbar, Nav } from "react-bootstrap";

const Header = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const [logged, setLogged] = useState(false);
  const [user, setUser] = useState(null);
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [username, setUsername] = useState(localStorage.getItem("username"));
  const storage = $("body");
  const [isNavOpen, setIsNavOpen] = useState(false);

  const handleToggleNav = () => {
    setIsNavOpen(!isNavOpen);
  };

  useEffect(() => {
    setToken(localStorage.getItem("token"));
    setUsername(localStorage.getItem("username"));
    if (token) {
      setLogged(true);
    }
  }, [token, storage]);

  useEffect(() => {
    const fetchUserByUsername = async () => {
      try {
        const response = await fetch(
          `http://localhost:3001/users/username/${username}`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );

        if (response.ok) {
          const data = await response.json();
          setUser(data);
        } else {
          throw new Error(
            "Errore durante il recupero dei dettagli dell'utente"
          );
        }
      } catch (error) {
        console.error(
          "Errore durante il recupero dei dettagli dell'utente:",
          error
        );
      }
    };

    fetchUserByUsername();
  }, [token, username]);

  const handleOurTravelClick = () => {
    navigate("/ourtravel");
  };
  const handleUsernameClick = () => {
    navigate("/myprenotations"); // Naviga alla rotta di MyPrenotations
  };

  const logout = () => {
    localStorage.clear();
    setToken(null);
    setLogged(false);
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
                        style={{ width: "100px", height: "50px" }}
                        alt="logo"
                      />
                    </a>
                  </div>
                </div>
              </div>
            </div>
            <div className="col-xl-9 col-lg-9 col-md-9 col-sm-9">
              <nav
                className={`navigation navbar navbar-expand-md navbar-dark ${
                  isNavOpen ? "show" : ""
                }`}
              >
                <button
                  className="navbar-toggler"
                  type="button"
                  onClick={handleToggleNav} // Usa la funzione di toggle qui
                >
                  <span className="navbar-toggler-icon" />
                </button>
                <div
                  className={`collapse navbar-collapse`}
                  id="navbarsExample04"
                >
                  <ul className="navbar-nav mr-auto">
                    <li className="nav-item">
                      <Link
                        className={`nav-link ${
                          location.pathname === "/" ? "active" : ""
                        }`}
                        to="/"
                      >
                        Home
                      </Link>
                    </li>
                    {logged && username && (
                      <>
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
                              location.pathname === "/myprenotations"
                                ? "active"
                                : ""
                            }`}
                            to="/myprenotations"
                            onClick={handleUsernameClick}
                          >
                            {username}
                          </Link>
                        </li>
                        <li>
                          <a
                            className="nav-link"
                            href="#logout"
                            onClick={() => {
                              logout();
                            }}
                          >
                            Esci
                          </a>
                        </li>
                      </>
                    )}
                    {!logged && (
                      <>
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
                              location.pathname === "/sign-in" ? "active" : ""
                            }`}
                            to="/sign-in"
                          >
                            Register
                          </Link>
                        </li>
                      </>
                    )}
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
