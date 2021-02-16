import {
    AppBar,
    Box,
    Container,
    makeStyles,
    withStyles,
    Toolbar,
    Typography,
} from "@material-ui/core";
import { ChevronRight } from "@material-ui/icons";
import React, { useState } from "react";

import logo from "../assets/logo.svg";
import backgroundImg from "../assets/background.jpg";
import NfRedButton from "../components/NfRedButton";
import NfValidatedTextField from "../components/NfValidatedTextField";
import { useHistory } from "react-router-dom";
import { validateEmail } from "../validators";

const useStyles = makeStyles({
    paper: {
        backgroundImage: `linear-gradient(to top,
			rgba(0,0,0,.8) 0,
			rgba(0,0,0,.4) 60%,
			rgba(0,0,0,.8) 100%), url(${backgroundImg})`,
        height: 750,
    },
    header: {
        boxShadow: "none",
        background: "transparent",
        height: "64px",
        padding: "25px",
    },
    logoBox: {
        display: "flex",
        flexGrow: 1,
    },
    centerForm: {
        padding: "150px 50px 0",
    },
    textField: {
        "& .MuiFormHelperText-root": {
            color: "#ffa00a",
            fontSize: "15px",
        },
    },
});

const WhiteTypography = withStyles({
    root: {
        color: "white",
    },
})(Typography);

export default function Landing() {
    const history = useHistory();
    const [shouldValidate, setShouldValidate] = useState(false);
    const submit = (e) => {
        e.preventDefault();
        const email = e.target.email.value;
        const isValid = validateEmail(email);
        if (isValid !== true) {
            return;
        }

        sessionStorage.setItem("email", email);
        history.push("/signup/registration");
    };

    const classes = useStyles();
    return (
        <div className={classes.paper}>
            <AppBar position="static" className={classes.header}>
                <Toolbar>
                    <Box className={classes.logoBox}>
                        <img height="36" src={logo} alt="logo" />
                    </Box>
                    <NfRedButton onClick={() => history.push("/signin")}>
                        Sign In
                    </NfRedButton>
                </Toolbar>
            </AppBar>

            <Container maxWidth="sm" className={classes.centerForm}>
                <WhiteTypography paragraph variant="h3" align="center">
                    <Box fontWeight="Bold" component="span">
                        Unlimited movies, TV shows, and more.
                    </Box>
                </WhiteTypography>
                <WhiteTypography paragraph variant="h5" align="center">
                    Watch anywhere. Cancel anytime.
                </WhiteTypography>
                <form align="center" onSubmit={submit}>
                    <WhiteTypography gutterBottom variant="h5" align="center">
                        Ready to watch? Enter your email to create or restart
                        your membership.
                    </WhiteTypography>
                    <NfValidatedTextField
                        type="email"
                        name="email"
                        fullWidth
                        label="Email address"
                        required
                        className={classes.textField}
                        shouldValidate={shouldValidate}
                        initialValue={sessionStorage.getItem("email")}
                    />
                    <br />
                    <NfRedButton
                        type="submit"
                        endIcon={<ChevronRight />}
                        style={{ minHeight: "40px" }}
                        onClick={() => setShouldValidate(true)}
                    >
                        GET STARTED
                    </NfRedButton>
                </form>
            </Container>
        </div>
    );
}
