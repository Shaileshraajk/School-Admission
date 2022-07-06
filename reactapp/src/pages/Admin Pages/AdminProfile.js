import React from 'react';
import Navbar from './Admin Components/Navbar';
import {adname, email, mobile} from '../OTPLogin'
// import background from './../../assets/adminpage1.jpg'
import { StyledContainer1 } from '../../components/Styles';
const AdminProfile = () => {
return (
	<StyledContainer1>
	<div style={{
		// backgroundImage: `url(${background})`,
		// backgroundColor: "transparent",
		height: '100vh'
	}}>
	<Navbar />
	<div style={{
                display: 'flex',
				justifyContent: 'center',
				alignItems: 'flex-start'
            }}>
                <h1 style={{color:"#fff",fontFamily:"Dosis",fontSize:"50px"}}>Welcome, {adname}</h1>
    </div>
	<span>
		<h2 style={{color:"	#fff",fontFamily:"Dosis",fontSize:"35px"}}>Email: {email}</h2>
        <h2 style={{color:"	#fff",fontFamily:"Dosis",fontSize:"35px"}}>Mobile: {mobile}</h2>
	</span>
	</div>
	</StyledContainer1>
);
};

export default AdminProfile;
