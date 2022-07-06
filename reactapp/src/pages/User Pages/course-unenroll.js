import React, { Component } from "react";
import CoursesServiceUser from "./CourseService-User/CoursesService-User";
import {emailadd} from './../Login'
import { Link } from "react-router-dom";
import {TextLink, StyledFormArea, StyledTitle, colors, EnrolledContainer} from './../../components/Styles'

export default class CourseUnenroll extends Component{

    constructor(props) {
        super(props);
        this.getEnrolledCourse = this.getEnrolledCourse.bind(this);
        this.unenrollCourse = this.unenrollCourse.bind(this);
    
        this.state = {
          currentEnrolledCourse: {
            regid: null,
            useremail: "",
            classno: "",
            instituteName: "",
            academicYear: "",
            scoredmarks: null
          },
          unenrolled: false
        };
      }

      componentDidMount() {
        this.getEnrolledCourse(this.props.match.params.id);
      }

      getEnrolledCourse(id) {
        CoursesServiceUser.getEnrolled(id)
        .then(response => {
            this.setState({
                currentEnrolledCourse: response.data
            })
            console.log(response.data);
        })
        .catch(e => {
            console.log(e);
        })
      }

      unenrollCourse(){
        var email = emailadd
        var regid = this.state.currentEnrolledCourse.regid
        CoursesServiceUser.deleteEnrolled(regid, email)
        .then(response => {
            this.setState({
                unenrolled: true
            })
            console.log(response.data)
        })
        .catch(e => {
            console.log(e)
        })
      }


      render(){
        const { currentEnrolledCourse } = this.state;  
        return(
            <EnrolledContainer>
                {this.state.unenrolled ? (
                    <EnrolledContainer>
                        <StyledFormArea>
                            <StyledTitle color={colors.green} size={25}>
                                <strong>
                                You have deleted your application successfully!
                                </strong>
                            </StyledTitle>
                            <StyledTitle color={colors.dark2} size={20}>
                               <TextLink to="/courseenrolled">View Applied Class</TextLink>
                            </StyledTitle>
                            <StyledTitle color={colors.dark2} size={20}>
                              Apply another Class ? <TextLink to="/usercourse">Click Here</TextLink>
                          </StyledTitle>
                        </StyledFormArea>
                    </EnrolledContainer>
                ): (
                    <EnrolledContainer>
                        <StyledFormArea>
                            <StyledTitle color={colors.theme} size={25}>
                                Do you wish to delete application for
                                <br></br>
                                <strong>
                                {currentEnrolledCourse.classno}
                                </strong> ?
                            </StyledTitle>
                            <button type="submit" onClick={this.unenrollCourse} className="btn btn-danger">
                                Delete
                            </button>
                            {" "}
                            <Link className="btn btn-success" to={'/courseenrolled'}>Cancel</Link>
                        </StyledFormArea>
                    </EnrolledContainer>
                )}
            </EnrolledContainer>
        )
      }


    }

