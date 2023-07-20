const MyFooter = () => {
  return (
    <footer>
      <div className="footer">
        <div className="container">
          <div className="row">
            <div className=" col-md-4">
              <h3>Contact US</h3>
              <ul className="conta">
                <li>
                  <i className="fa fa-map-marker" aria-hidden="true" /> Address
                </li>
                <li>
                  <i className="fa fa-mobile" aria-hidden="true" />
                  3464105830
                </li>
                <li>
                  {" "}
                  <i className="fa fa-envelope" aria-hidden="true" />
                  <a href="#"> luca.pratico@live.it</a>
                </li>
              </ul>
            </div>
            <div className="col-md-4">
              <h3>Menu Link</h3>
              <ul className="link_menu">
                <li>
                  <a href="about.html"> About</a>
                </li>
                <li>
                  <a href="room.html">Our Travels</a>
                </li>

                <li>
                  <a href="contact.html">Contact Us</a>
                </li>
              </ul>
            </div>
            <div className="col-md-4 ">
              <h3>Socials</h3>

              <ul className="social_icon">
                <li>
                  <a href="https://www.facebook.com/luca.pratico1/">
                    <i className="fa fa-facebook" aria-hidden="true" />
                  </a>
                </li>
                <li>
                  <a href="https://twitter.com/">
                    <i className="fa fa-twitter" aria-hidden="true" />
                  </a>
                </li>
                <li>
                  <a href="https://www.linkedin.com/in/gianluca-pratic%C3%B2-688886253/">
                    <i className="fa fa-linkedin" aria-hidden="true" />
                  </a>
                </li>
                <li>
                  <a href="https://github.com/Gianlucapratico">
                    <i className="fa fa-github" aria-hidden="true"></i>
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <div className="copyright">
        <div className="container">
          <div className="row">
            <div className="col-md-10 offset-md-1">
              <p>© 2023 All Rights Reserved. Design by{" Gianluca Praticò"}</p>
            </div>
          </div>
        </div>
      </div>
    </footer>
  );
};
export default MyFooter;
