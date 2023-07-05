import { BrowserRouter, Routes, Route } from "react-router-dom";

import MyFooter from "./components/MyFooter";
import About from "./components/about";
import Banner from "./components/banner";
import Header from "./components/header";
import OurTravel from "./components/ourTravel";
import "./css/style.css";
import "./js/custom.js";
import Login from "./components/Login";
import Home from "./components/Home";
import SignIn from "./components/SignIn";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Header></Header>

        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/sign in" element={<SignIn />} />
          <Route path="/ourtravel" element={<OurTravel />} />
          <Route path="/about" element={<About />} />
          <Route path="/banner" element={<Banner />} />
        </Routes>

        <MyFooter></MyFooter>
      </BrowserRouter>
    </div>
  );
}

export default App;
