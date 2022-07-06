import React, { Component} from "react";
import CoursesDataService from "../CourseService-User/CoursesService-User";
import {StyledButton} from './../../../components/Styles'




export default class CoursesList extends Component{

    constructor(props) {
        super(props);
        this.onChangeSearchTitle = this.onChangeSearchTitle.bind(this);
        this.retrieveCourses = this.retrieveCourses.bind(this);
        this.refreshList = this.refreshList.bind(this);
        this.setActiveCourse = this.setActiveCourse.bind(this);
        this.searchTitle = this.searchTitle.bind(this);
    
        this.state = {
          courses: [],
          currentCourse: null,
          currentIndex: -1,
          searchTitle: "",
          coursesavailable: false
        };
      }

      componentDidMount() {
        this.retrieveCourses();
      }

      onChangeSearchTitle(e) {
        const searchTitle = e.target.value;
    
        this.setState({
          searchTitle: searchTitle
        });
      }


      retrieveCourses() {
        CoursesDataService.getAll()
          .then(response => {
            this.setState({
                courses: response.data
            });
            var nocourses = this.state.courses.length
            if(nocourses===0){
              this.setState({
                coursesavailable: false
              })
            }
            else{
              this.setState({
                coursesavailable: true
              })
            }
          })
          .catch(e => {
            console.log(e);
          });
      }

      refreshList() {
        this.retrieveCourses();
        this.setState({
            currentCourse: null,
          currentIndex: -1
        });
      }

      setActiveCourse(course, index) {
        this.setState({
            currentCourse: course,
          currentIndex: index
        });
      }

      searchTitle() {
        this.setState({
          currentTutorial: null,
          currentIndex: -1
        });
    
        CoursesDataService.findByClassNo(this.state.searchTitle)
          .then(response => {
            this.setState({
                courses: response.data
            });
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
      }

      

      render() {

        const { searchTitle, courses, currentCourse, currentIndex} = this.state;
        return (
          <div>
            {this.state.coursesavailable ? (
              <div className="list row">
              <div className="col-md-8">
                  <div className="input-group mb-3">
                  <input
                      type="text"
                      className="form-control"
                      placeholder="Search by Class Number (Class X)"
                      value={searchTitle}
                      onChange={this.onChangeSearchTitle}
                  />
                  <div className="input-group-append">
                      <button
                          className="btn btn-primary"
                          type="button"
                          onClick={this.searchTitle}
                      >
                          Search
                      </button>
                  </div>
                  </div>
              </div>
              <div className="col-lg-6">
                  <h4 class="text-white bg-dark">Courses List</h4>

                  <ul className="list-group list-group-numbered">
                      {courses &&
                          courses.map((course, index) => (
                              <li  
                                className={
                                      "list-group-item"+
                                      (index===currentIndex ? " active" : "")
                                  }
                                  onClick={() => this.setActiveCourse(course, index)}
                                  key={index}
                              >
                                  {course.classno} - {course.institute_name}
                              </li>
                          ))}
                  </ul>
              </div>
              <div className="col-md-6">
                  {currentCourse ? (
                      <div>
                          <h4 class="text-warning"><strong>Class Details</strong></h4>
                          <div class="p-3 mb-2 bg-success text-white">
                              <label>
                                  <strong>Class Number:</strong>
                              </label>{" "}
                              {currentCourse.classno}
                          </div>
                          <div class="p-3 mb-2 bg-warning text-dark">
                              <label>
                                  <strong>Offered by:</strong>
                              </label>{" "}
                              {currentCourse.institute_name}
                          </div>
                          <div class="p-3 mb-2 bg-danger text-white">
                              <label>
                                  <strong>Academic Year:</strong>
                              </label>{" "}
                              {currentCourse.academicYear}
                          </div>
                          <div class="p-3 mb-2 bg-dark text-white">
                              <label>
                                  <strong>Eligibility Criteria:</strong>
                              </label>{" "}
                              Minimum of {currentCourse.elgibleMarks}% marks from a recognized Board.
                          </div>
                             <StyledButton to={"/enroll/" + currentCourse.classid}>Apply</StyledButton>
                      </div>
                  ):(
                      <div class="p-3 mb-2 bg-warning text-dark">
                          <strong>Please click on a Course...</strong>
                      </div>
                  )}
              </div>
              
          </div>
            ): (
              <div>
                  <br></br>
                  <h2 className="text-white text-center">No Courses Enrolled</h2> 
              </div>
            )}
          </div>

        )

      }

}