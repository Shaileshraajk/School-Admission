import React from 'react';
import PremiumLogo from './Logo1.JPG'
import {ButtonGroup, StyledButton, StyledFormArea, StyledContainer, Avatar, StyledTitle, colors, PreLogo, CloseIcon} from './../../components/Styles'

const PremiumPopup = ({ open, onClose }) => {
    if (!open) return null;
    return (
        // <div onClick={onClose} className='overlay'>
        //     <div
        //         onClick={(e) => {
        //             e.stopPropagation();
        //           }}
        //           className='modalContainer'
        //     >
        //         <img src={PremiumLogo} alt='/' />
        //         <div className='modalRight'>
        //             <p className='closeBtn' onClick={onClose}>
        //                 X
        //             </p>
        //             <div className='content'>
        //                 <p>Do you want a</p>
        //                 <h1>$20 CREDIT</h1>
        //                 <p>for your first tade?</p>
        //             </div>
        //             <ButtonGroup>
        //                 <button className='btn btn-success'>Yes</button>
        //             </ButtonGroup>
        //         </div>

        //     </div>
        // </div>

        <div className='overlay' onClick={onClose}>
            <StyledFormArea 
                onClick={(e) => {
                e.stopPropagation();
            }}>
                <PreLogo image={PremiumLogo} />
                <CloseIcon onClick={onClose} className='btn btn-dark'>X</CloseIcon>
                <StyledTitle color={colors.theme} size={30}>Wanna be a Premium Member</StyledTitle>
                <ButtonGroup>
                    <button className='btn btn-success'>Yes</button>
                </ButtonGroup>
            </StyledFormArea>
        </div>
    )

}

export default PremiumPopup;