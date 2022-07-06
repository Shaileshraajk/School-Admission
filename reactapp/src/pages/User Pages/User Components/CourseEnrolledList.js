import React, { Component} from "react";
import CoursesDataService from "../CourseService-User/CoursesService-User";
import {TextLink, EnrolledContainer} from './../../../components/Styles'
import {emailadd} from './../../Login'

export default class EnrolledCoursesList extends Component{
    constructor(props) {
        super(props);
        this.retrieveCourses = this.retrieveCourses.bind(this);
    
        this.state = {
          enrolledcourses: [],
          enrolled: false
        };
      }

      componentDidMount() {
        this.retrieveCourses();
      }

      retrieveCourses() {
        CoursesDataService.viewEnrolled(emailadd)

          .then(response => {
            this.setState({
                enrolledcourses: response.data
            });
            var noenrolled = this.state.enrolledcourses.length
            if(noenrolled===0){
              this.setState({
                enrolled: false
            })
            }
            else{
              this.setState({
                enrolled: true
            })
            }
          })
          .catch(e => {
            console.log(e);
          });
      }

      render() {
        return(
            <EnrolledContainer>
              {this.state.enrolled ? (
                <div>
                <br></br>
                <h2 className="text-white text-center">Applied Courses</h2>
                <br></br>
                <div className = "row-md-6">
                       <table className = "table table-striped table-light table-bordered table-hover">

                           <thead className="text-center">
                               <tr>
                                   <th> Class Number</th>
                                   <th> Institute Name</th>
                                   <th> Academic Year</th>
                                   <th> Actions</th>
                               </tr>
                           </thead>
                           <tbody>
                               {
                                   this.state.enrolledcourses.map(
                                       enrolledcourse => 
                                       <tr key = {enrolledcourse.regid}>
                                            <td className="align-middle text-center"> { enrolledcourse.classno} </td>   
                                            <td className="align-middle text-center"> {enrolledcourse.instituteName}</td>
                                            <td className="align-middle text-center"> {enrolledcourse.academicYear}</td>
                                            <td className="align-middle text-center">
                                               <TextLink to={"/unenroll/" + enrolledcourse.regid}>Delete Application</TextLink>
                                            </td>
                                       </tr>
                                   )
                               }
                           </tbody>
                       </table>

                </div>

           </div>
              ): (
                <div>
                  <br></br>
                  <h2 className="text-white text-center">No Courses Enrolled</h2> 
                </div>
              )}
            </EnrolledContainer>
        )
      }

}