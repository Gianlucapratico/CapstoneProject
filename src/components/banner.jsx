const Banner = () => {
  return (
    <section className="banner_main">
      <div
        id="myCarousel"
        className="carousel slide banner"
        data-ride="carousel"
      >
        <ol className="carousel-indicators">
          <li data-target="#myCarousel" data-slide-to={0} className="active" />
          <li data-target="#myCarousel" data-slide-to={1} />
          <li data-target="#myCarousel" data-slide-to={2} />
        </ol>
        <div className="carousel-inner">
          <div className="carousel-item active">
            <img src={require("../images/banner1.jpg")} alt="First slide" />
            <div className="container"></div>
          </div>
          <div className="carousel-item">
            <img
              className="second-slide"
              src="images/banner2.jpg"
              alt="Second slide"
            />
          </div>
          <div className="carousel-item">
            <img src={require("../images/banner2.jpg")} alt="Second slide" />
          </div>
          <div className="carousel-item">
            <img src={require("../images/banner3.jpg")} alt="Third slide" />
          </div>
        </div>

        <a
          className="carousel-control-prev"
          href="#myCarousel"
          role="button"
          data-slide="prev"
        >
          <span className="carousel-control-prev-icon" aria-hidden="true" />
          <span className="sr-only">Previous</span>
        </a>
        <a
          className="carousel-control-next"
          href="#myCarousel"
          role="button"
          data-slide="next"
        >
          <span className="carousel-control-next-icon" aria-hidden="true" />
          <span className="sr-only">Next</span>
        </a>
      </div>
      <div className="booking_ocline">
        <div className="container">
          <div className="row">
            <div className="col-md-5">
              <div className="book_room">
                <h1>Book a Room Online</h1>
                <form className="book_now">
                  <div className="row">
                    <div className="col-md-12">
                      <span>Arrival</span>
                      <img
                        className="date_cua"
                        src={require("../images/date.png")}
                        alt="Date"
                      />
                      <input
                        className="online_book"
                        placeholder="dd/mm/yyyy"
                        type="date"
                        name="dd/mm/yyyy"
                      />
                    </div>
                    <div className="col-md-12">
                      <span>Departure</span>
                      <img
                        className="date_cua"
                        src={require("../images/date.png")}
                        alt="Date"
                      />
                      <input
                        className="online_book"
                        placeholder="dd/mm/yyyy"
                        type="date"
                        name="dd/mm/yyyy"
                      />
                    </div>
                    <div className="col-md-12">
                      <button className="book_btn">Book Now</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};
export default Banner;
