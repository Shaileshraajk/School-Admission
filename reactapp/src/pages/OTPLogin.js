import {StyledFormArea, StyledFormButton, Avatar, StyledTitle, colors, ButtonGroup, ExtraText, TextLink, CopyrightText, StyledContainer, StyledSubTitle} from './../components/Styles'
import RambosLogo from './../assets/RambosLogo.jpg';
import {Formik, Form} from 'formik';
import {TextInput} from './../components/FormLib';
import {FiMail, FiLock} from 'react-icons/fi';
import * as Yup from 'yup';
import {useHistory} from 'react-router-dom'
import React from 'react';
import {Redirect} from 'react-router-dom'
import { emailadd, name, mobno } from './AdminLogin';
import axios from 'axios';

let adminauthorized=false
let email=""
let adname=""
let mobile=""

const OTPLogin = () => {

    const data = {
        email: emailadd,
        name: name
    }

    const history  = useHistory();

    return (
        <StyledContainer>
            <div>
            <StyledFormArea>
                <Avatar image={RambosLogo} />
                <StyledTitle color={colors.theme} size={30}>Admin Login</StyledTitle>
                <StyledSubTitle color={colors.dark1} size={20}>An OTP has been sent to {emailadd}</StyledSubTitle>
                <Formik
                    initialValues={{
                        otp: ""
                    }}
                    validationSchema={
                        Yup.object({
                            otp: Yup.number().min(6).required()
                        })
                    }
                    onSubmit={(values) => {
                        console.log(values.otp);
                        axios.post(`http://localhost:8080/user/validateAdminOTP?otp=${values.otp}`,data).then(
                            response => {
                                console.log(response.data)
                                adminauthorized=true
                                email=emailadd
                                adname=name
                                mobile=mobno
                                history.push('/admin')

                            }
                        )

                    }}
                >
                
                {({isSubmitting}) => (
                    <Form>
                        <TextInput
                                name="otp"
                                type="password"
                                label="OTP"
                                placeholder="******"
                                icon={<FiLock />}
                            >

                            </TextInput>

                            <ButtonGroup>
                                <StyledFormButton type="submit">Submit</StyledFormButton>
                                <StyledFormButton type="reset">Reset</StyledFormButton>
                            </ButtonGroup>
                    </Form>
                )}

                </Formik>
                <ExtraText>
                    Resend OTP ? <TextLink to="/adminlogin">Click here</TextLink>
                </ExtraText>
            </StyledFormArea>
            </div>
        </StyledContainer>
    )

}
export {adname, mobile, email, adminauthorized}
export default OTPLogin;