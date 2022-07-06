import React,{useState} from 'react';
import Navbar from './User Components/Navbar'
import { name, mobno, emailadd } from '../Login';
import { Link } from 'react-router-dom';
import PremiumPopup from './PremiumPopup';

const Userprofile = () => {

	const [openPopup, setOpenPopup] = useState(false);

return (
	<div>
	<Navbar />
	<div className="row">
            <div className={"ban "+(openPopup?'blurred':'')}>
                <img src="./images/rr13.jpg" alt="" className="srs" />
                    <h2 className="headi">Welcome, {name}</h2>
					<h2 className="email">Email: {emailadd}</h2>
					<h2 className="mobile">Mobile: {mobno}</h2>
					<button className='btn btn-success paybutton' onClick={() => setOpenPopup(true)}>Become a Premium Member</button>
         </div>
		 <PremiumPopup open={openPopup} onClose={() => setOpenPopup(false)}/>
    </div>
	</div>
);
};

export default Userprofile;
