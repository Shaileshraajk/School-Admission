import React from 'react'
import { Link } from 'react-router-dom';
import { ListGroup, ListGroupItem } from 'reactstrap';

 const Menus=()=> {
  return (
    <ListGroup className='my-4'>
     
        <Link className='list-group-item list-group-item-action' tag="a" to="/admincourse/add-class" action>Add Class</Link>
        
        <Link className='list-group-item list-group-item-action' tag="a" to="/admincourse/view-class" action>View Classes</Link>
      
    </ListGroup>
  );
};
export default Menus;