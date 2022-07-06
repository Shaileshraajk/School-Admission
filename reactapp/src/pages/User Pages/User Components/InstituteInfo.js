import React, { Component } from "react";
import InstituteDataService from '../Institute-Service/InstituteService-User'
import CollegeCard from './CollegeCard'
import Navbar from "./Navbar";
import Rating from '@mui/material/Rating';
import {TextLink, StyledFormArea, StyledTitle, CoursesContainer, colors, Avatar, InstituteInfoContainer} from './../../../components/Styles'

export default class InstituteInfo extends Component{
    constructor(props){
        super(props)
        this.retreiveInfo = this.retreiveInfo.bind(this)
        this.retreiveRating = this.retreiveRating.bind(this)

        this.state = {
            currentInstitute: {
                institutesid: null,
                institueName: "",
                institutePlace: "",
                imgLocation: "",
                rating: null,
                instituteDescription: ""
            },
            ratingdata: []
        }

    }

    componentDidMount(){
        this.retreiveInfo(this.props.match.params.id)
    }

    retreiveInfo(id){
        InstituteDataService.findById(id)
        .then(response => {
            console.log(response.data)
            this.setState({
                currentInstitute: response.data
            })
            this.retreiveRating(this.state.currentInstitute.institueName)
        })
        .catch(error => {
            console.log(error)
        })
    }

    retreiveRating(instituteName){
        InstituteDataService.getRating(instituteName)
        .then(response => {
            //console.log(response.data)
            this.setState({
                ratingdata: response.data
            })
        })
        .catch(error=>{
            console.log(error)
        })
    }

    render(){
        const {currentInstitute, ratingdata} = this.state
        return(
            <InstituteInfoContainer>
                <Navbar />
                 <StyledTitle color={colors.light1} size={25}>
                        <strong>{currentInstitute.institueName} - {currentInstitute.institutePlace}</strong>
                    </StyledTitle>
                 <img src={process.env.PUBLIC_URL + '/' +currentInstitute.imgLocation}  className="rounded mx-auto d-block" alt="College" width={500} height={300}/>
                 <br></br>
                    <p className="text-justify text-white h4">{currentInstitute.instituteDescription}</p>
                <br></br>
                    <h4 className="text-white"><strong>User Reviews : </strong></h4>
                {this.state.ratingdata.length>0?(
                    this.state.ratingdata.map((currentReviews) => {
                        const {name, review, rating} = currentReviews
                        return(
                            <div className="card w-75 p-1">
                                <div className="card-header">
                                    <strong>
                                    {name} -
                                    </strong>
                                    <Rating name="half-rating-read" value={rating} precision={0.5} readOnly size="small"/>
                                </div>
                                <div className="card-body">
                                    <p className="card-text">{review}</p>
                                </div>
                                
                            </div>
                        )
                    })
                ):(
                    <>
                    </>
                )}
            </InstituteInfoContainer>
        )
    }


}