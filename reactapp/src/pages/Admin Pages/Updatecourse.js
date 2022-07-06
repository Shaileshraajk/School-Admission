import React, { Fragment, useEffect, useState } from 'react'
import { Form, FormGroup, Input, Label, Container, Button } from 'reactstrap';
import axios from 'axios';
import base_url from '../../coursesapi';
import { Link } from 'react-router-dom';
import { toast } from 'react-toastify';

function Updatecourse(props) {
  const[courses,setCourses]=useState({});
  const Handleform=(e)=>{
    //updated component
    // const {id}=useParams();
    // console.log(id);
    // console.log(props.match.params.id);
    console.log(courses);
    
    UpdateDatatoServer(courses);
    e.preventDefault();
  }

  const getCourseInfo=()=>{
    axios.get(`${base_url}/classes/getbyId?id=${props.match.params.id}`)
    .then(response => {
      console.log(response.data)
      setCourses(response.data)
      //console.log(courses.title)
    })
    .catch(error => {
      console.log(error)
    })
  }

  useEffect(()=>{
    getCourseInfo();
  },{})
  
  const UpdateDatatoServer=(data)=>{
    

    axios.put(`${base_url}/classes/edit/${props.match.params.id}`,data).then(
      (response)=>{
          console.log(response);
          toast.success("Course updated successfully");
      },
      (error)=>{
        // console.log(error);
        //console.log(error);
        console.log(error);
        toast.error("Course was not updated");
      }
    );
  };
  return (
      <Fragment>
      <h1 className='text-center my-3'>Update Course here</h1>
      <Form onSubmit={Handleform}>
           {/* <FormGroup>
          <Label for="userId" >Course id</Label>
          <Input type="text" name="userId" id="userId" placeholder="Enter course id here" 
          onChange={(e)=>{
            setCourses({...courses,courseid:e.target.value})
          }}/>
          </FormGroup> */}
          
          <FormGroup>
          <Label for="title" ><strong>Class Number</strong></Label>
          <Input type="text" name="title" id="title" placeholder="Enter class number here" required
          value={courses.classno}
          readOnly
          />
          </FormGroup>
          <FormGroup>
          <Label for="userId" ><strong>Institute Id</strong></Label>
          <Input type="text" name="userId" id="userId" placeholder="Enter institute id here" required
          value={courses.instituteid}
          onChange={(e)=>{
            setCourses({...courses,instituteid:e.target.value})
          }}/>
          </FormGroup>
          <FormGroup>
          <Label for="title" ><strong>Institute Name</strong></Label>
          <Input type="text" name="title" id="title" placeholder="Enter institute name here" required
          value={courses.institute_name}
          onChange={(e)=>{
            setCourses({...courses,institute_name:e.target.value})
          }}/>
          </FormGroup>
          
          <FormGroup>
          <Label for="title" ><strong>Academic Year</strong></Label>
          <Input type="text" name="title" id="title" placeholder="Enter academic year" required
          value={courses.academicYear}
          onChange={(e)=>{
            setCourses({...courses,academicYear:e.target.value})
          }}/>
          </FormGroup>
          <FormGroup>
          <Label for="title" ><strong>Eligibility Marks</strong></Label>
          <Input type="text" name="title" id="title" placeholder="Enter eligible marks" required
          value={courses.elgibleMarks}
          onChange={(e)=>{
            setCourses({...courses,elgibleMarks:e.target.value})
          }}/>
          </FormGroup>
      <Container className='text-center'>
          <Button type="submit" color="warning">Update</Button>
          {" "}
          <Link className='btn btn-success' to={'/admincourse/view-class'}>Cancel</Link>
        </Container>
     </Form>
     </Fragment>
  );
};
export default Updatecourse;