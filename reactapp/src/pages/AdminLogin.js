import {StyledFormArea, StyledFormButton, Avatar, StyledTitle, colors, ButtonGroup, ExtraText, TextLink, CopyrightText, StyledContainer} from './../components/Styles'
import RambosLogo from './../assets/RambosLogo.jpg';
import {Formik, Form} from 'formik';
import {TextInput} from './../components/FormLib';
import {FiMail, FiLock} from 'react-icons/fi';
import * as Yup from 'yup';
import {useHistory} from 'react-router-dom'
import React, { useState } from 'react';
import Loader from 'react-loader-spinner'


let name=""
let mobno=""
let emailadd=""
let errormsg=""

const AdminLogin = () => {
    const history  = useHistory();
    const [submitted, setSubmitted] = useState(false);
    return(
        <StyledContainer>
            <div>
                <StyledFormArea>
                <Avatar image={RambosLogo} />
                <StyledTitle color={colors.theme} size={30}>Admin Login</StyledTitle>
                <Formik 
                    initialValues={{
                        email: ""
                    }}
                    validationSchema={
                        Yup.object({
                            email: Yup.string().email("Invalid email id")
                            .required("Required")
                        })
                    }
                    onSubmit={(values) => {
                        console.log(JSON.stringify(values, null, 4));
                        fetch("http://localhost:8080/user/loginasAdmin",{
                            method:"POST",
                            headers:{"Content-Type":"application/json"},
                            body:JSON.stringify(values)

                            }).then((response) => {
                                if(response.status===200){
                                    response.json().then(d => {
                                        emailadd=d.email
                                        name=d.name
                                        mobno=d.mobno
                                        history.push('/otplogin')
                                        setSubmitted(true)
                                    })
                                    
                                }
                                else
                                {
                                    throw Error('Invalid Admin Credentials')
                                }
                            })
                            .catch(error => {
                                console.log(error.message)
                                errormsg = error.message
                                history.push('/invalidlogin')
                            })
                    }}
                >
                    {({isSubmitting}) => (
                        <Form>
                            <TextInput
                                name="email"
                                type="text"
                                label="Email Address"
                                placeholder="xyz@example.com"
                                icon={<FiMail />}
                            >

                            </TextInput>


                            {isSubmitting?(
                                <Loader 
                                    type="ThreeDots"
                                    color="#ff80aa"
                                    weight={50}
                                    height={49}
                                />
                            ):(
                                <ButtonGroup>
                                    <StyledFormButton type="submit">Submit</StyledFormButton>
                                    <StyledFormButton type="reset">Reset</StyledFormButton>
                                </ButtonGroup>
                            )}



                            

                            
                            
                        </Form>
                    )}
                </Formik>
                <ExtraText>
                    Student/Parent ? <TextLink to="/">Click here</TextLink>
                </ExtraText>
                </StyledFormArea>
            </div>
        </StyledContainer>
    )
}
export {emailadd, name, mobno};
export default AdminLogin;