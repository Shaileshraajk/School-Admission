//Login and Signup
import React from 'react';
import Home from './pages/Home';
import Login from './pages/Login';
import Signup from './pages/Signup';
import Dashboard from './pages/Dashboard';
import UserAdded from './pages/UserAdded'
import UsernotAdded from './pages/UsernotAdded';
import InvalidLogin from './pages/InvalidLogin';
import { authorized} from './pages/Login';

//Admin
import Admin from './pages/Admin';
import OTPLogin from './pages/OTPLogin';
import AdminLogin from './pages/AdminLogin';
import Course from './pages/Admin Pages/course';
import Students from './pages/Admin Pages/students'
import Institute from './pages/Admin Pages/institute';
import AdminProfile from './pages/Admin Pages/AdminProfile';
import AdmitStudent from './pages/Admin Pages/AdmitStudent';
import DenyAdmission from './pages/Admin Pages/DenyAdmission';
import AddAcademy from './pages/Admin Pages/AddAcademy';
import EditAcademy from './pages/Admin Pages/EditAcademy';
import DeleteAcademy from './pages/Admin Pages/DeleteAcademy';
import { adminauthorized } from './pages/OTPLogin';

//User
import User from './pages/User';
import userCourse from './pages/User Pages/userCourse'
import CourseEnrolled from './pages/User Pages/courseenrolled';
import UserInstitute from './pages/User Pages/UserInstitute';
import Userprofile from './pages/User Pages/Userprofile';
import CourseEnroll from './pages/User Pages/enroll-course';
import CourseUnenroll from './pages/User Pages/course-unenroll';
import UserRating from './pages/User Pages/User Components/UserReviewRating';
import InstituteInfo from './pages/User Pages/User Components/InstituteInfo';



import {
  BrowserRouter as Router,
  Switch,
  Route
} from 'react-router-dom';
//Loader css
import "react-loader-spinner/dist/loader/css/react-spinner-loader.css";

function App() {
  return (
    <Router>
        <Switch>

          <Route path="/signup">
            <Signup />
          </Route>

          <Route path="/useradded">
            <UserAdded />
          </Route>

          <Route path="/usernotadded">
            <UsernotAdded />
          </Route>

          <Route path="/login">
            <Login />
          </Route>

          <Route path="/adminlogin">
            <AdminLogin />
          </Route>

          <Route path="/invalidlogin">
            <InvalidLogin />
          </Route>

          <Route path="/dashboard">
            <Dashboard />
          </Route>

          <Route path="/otplogin">
            <OTPLogin />
          </Route>

          <Route path="/enroll/:id" component={CourseEnroll} />

          <Route path="/rate/:id" component={UserRating} />

          <Route path="/info/:id" component={InstituteInfo} />

          <Route path="/unenroll/:id" component={CourseUnenroll} />
          <Route path="/admin" component={()=> <Admin adminauthorized={adminauthorized}/>}/>

          <Route path='/adminprofile' component={AdminProfile}/>
		      <Route path='/admininstitute' component={Institute} />
		      <Route path='/admincourse' component={Course} />
		      <Route path='/adminstudents' component={Students}/>

          <Route path='/addacdemy' component={AddAcademy}/>
          <Route path="/editAcademy/:id" component={EditAcademy} />
          <Route path="/deleteAcademy/:id" component={DeleteAcademy} />

          <Route path="/admit/:id" component={AdmitStudent} />
          <Route path="/deny-admission/:id" component={DenyAdmission} />
          

          <Route path="/user" component={()=> <User authorized={authorized}/>}/>


          <Route path='/userprofile' component={Userprofile}/>
		      <Route path='/userinstitute' component={UserInstitute} />
		      <Route path='/usercourse' component={userCourse} />
		      <Route path='/courseenrolled' component={CourseEnrolled}/>



          <Route path="/">
            <Home />
          </Route>

        </Switch>

      
    </Router>
    
  );
}

export default App;
