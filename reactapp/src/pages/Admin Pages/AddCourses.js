
import React, { Fragment, useEffect, useState } from 'react'
import { Form, FormGroup, Input, Label, Container, Button } from 'reactstrap';
import axios from 'axios';
import base_url from '../../coursesapi';
import 'react-toastify/dist/ReactToastify.css';
import { toast } from 'react-toastify';
toast.configure();
const AddCourses=()=> {
  const[courses,setCourses]=useState({});
  const handleform=(e)=>{
    console.log(courses);
    postDatatoServer(courses);
    e.preventDefault();
  }
  const postDatatoServer=(data)=>{
    axios.post(`${base_url}/classes/add`,data).then(
      (response)=>{
          console.log(response);
          if(response.status===200){
          // console.log("success");
          toast.success("Course added successfully");
          }
          else{
            toast.warning("Course was already added");
          }
          // setCourses({course_id:"",title:"",instituteid:"",course_desc:"",institute_name:""});
      },
      (error)=>{
        console.log(error);
        console.log("error");
        toast.error("Something went wrong");
      }
    );
  };
  return (
      <Fragment>
      <h1 className='text-center my-3'>Add Course here</h1>
      <Form onSubmit={handleform}>
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
          onChange={(e)=>{
            setCourses({...courses,classno:e.target.value})
          }}/>
          </FormGroup>
          <FormGroup>
          <Label for="userId" ><strong>Institute Id</strong></Label>
          <Input type="text" name="userId" id="userId" placeholder="Enter institute id here" required
          onChange={(e)=>{
            setCourses({...courses,instituteid:e.target.value})
          }}/>
          </FormGroup>
          
          <FormGroup>
          <Label for="title" ><strong>Institute Name</strong></Label>
          <Input type="text" name="title" id="title" placeholder="Enter institute name here" required
          onChange={(e)=>{
            setCourses({...courses,institute_name:e.target.value})
          }}/>
          </FormGroup>
          <FormGroup>
          <Label for="title" ><strong>Academic Year</strong></Label>
          <Input type="text" name="title" id="title" placeholder="Enter academic year" required
          onChange={(e)=>{
            setCourses({...courses,academicYear:e.target.value})
          }}/>
          </FormGroup>
          <FormGroup>
          <Label for="title" ><strong>Eligibility Marks</strong></Label>
          <Input type="text" name="title" id="title" placeholder="Enter eligibility marks" required
          onChange={(e)=>{
            setCourses({...courses,elgibleMarks:e.target.value})
          }}/>
          </FormGroup>
      <Container className='text-center'>
          <Button type="submit" color="success">Add</Button>
          {"  "}
          <Button type="reset"color="warning ml-3">Clear</Button>
        </Container>
     </Form>
     </Fragment>
  );
};
export default AddCourses