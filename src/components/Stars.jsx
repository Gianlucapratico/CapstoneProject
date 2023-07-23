const Stars = ({ starNumber }) => {
  var stars = "";

  for (let index = 1; index < Number(starNumber + 1); index++) {
    stars += "★";
  }
  return stars;
};

export default Stars;
