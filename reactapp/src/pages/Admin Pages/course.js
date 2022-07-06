import React from "react";
import Navbar from "./Admin Components/Navbar";
// import background from "./../../assets/courses1.jpg";
import {
  Container,
  Row,
  Col,
} from "reactstrap";
import Allcourses from "./Allcourses";
import AddCourses from "./AddCourses";
import Header from "./Header";
import Menus from "./Menus";

import {
	BrowserRouter as Router,
	Switch,
	Route,
	Link
  } from "react-router-dom";
import { ToastContainer } from "react-bootstrap";
import Updatecourse from "./Updatecourse";
import DeleteCourse from "./DeleteCourse"; 

const Course = () => {
  return (
	
    
	 
      /* <div style={{
                display: 'flex',
				justifyContent: 'center',
				alignItems: 'flex-start',
				height: '100vh',
			//	backgroundImage:`url(${background})`
            }}>
	<h1 style={{color:"#000000",fontSize:"60px",fontFamily:"Dosis"}}>Courses available</h1>
	</div> */

	 
	    <div>
        <Navbar />
		<Router>
		
    <ToastContainer/>
	  <Container>
		  <Header/>
		  <Row>
			  <Col md={4}>
				  <Menus/>
			  </Col>
			  <Col md={8}>
			  <Route path="/admincourse/add-class" component={AddCourses} exact/>
			  <Route path="/admincourse/view-class" component={Allcourses} exact/>
			  <Route path="/admincourse/view-class/update/:id" component={Updatecourse} exact/>
			  <Route path="/admincourse/view-class/delete/:id" component={DeleteCourse} exact/>

			  </Col>
		  </Row>
	  </Container>
	  	
	  </Router>
	  </div>
    
	
	
  );
};

export default Course;
