import { StyledTitle, StyledSubTitle, Avatar, StyledButton, ButtonGroup, CopyrightText, StyledContainer, ExtraText, TextLink, colors} from "../components/Styles";
import RambosLogo from './../assets/RambosLogo.jpg';

const Home = () => {
    return(
        <StyledContainer>
        <div>
            <div style={{
                position: "absolute",
                top: 90,
                right: 500,
                backgroundColor: "transparent",
                width: "100%",
                padding: "15px"
            }}>
                <Avatar image={RambosLogo} />
            </div>
            <StyledTitle size={65}>
                Welcome to RAMBOS Admission
            </StyledTitle>
            <StyledSubTitle size={27}>
                India's #1 Web Admission Portal
            </StyledSubTitle>
            <ButtonGroup>
            <StyledButton to="/login">Login</StyledButton>
            <StyledButton to="/signup">Signup</StyledButton>
            </ButtonGroup>
            {/* <ExtraText>
                Admins - <TextLink to={'/adminlogin'}>Click here</TextLink>
            </ExtraText> */}
            <CopyrightText>
                All rights reserved &copy; 2022
            </CopyrightText>
            <div style={{
                position: "absolute",
                bottom: 90,
                right: 500,
                backgroundColor: "transparent",
                width: "100%",
                padding: "15px"
            }}>
                <ExtraText color={colors.light1}>
                Admins - <TextLink color={colors.light2} to={'/adminlogin'}>Click here</TextLink>
                </ExtraText>
            </div>
        </div>
        </StyledContainer>
    );
}

export default Home;