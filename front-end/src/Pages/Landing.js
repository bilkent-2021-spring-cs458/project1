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
import React, { useEffect, useRef, useState } from "react";
import logo from "../assets/logo.svg";
import backgroundImg from "../assets/background.jpg";
import NfRedButton from "../components/NfRedButton";
import NfTextField from "../components/NfTextField";

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
});

const WhiteTypography = withStyles({
    root: {
        color: "white",
    },
})(Typography);

export default function Landing() {
    const [email, setEmail] = useState("");
    const [error, setError] = useState("");

    // Validating Email
    const validateEmail = function () {
        if (email.length < 5) {
            setError("Email is required!");
            return;
        }

        const pattern = /\S+@\S+\.\S+/;
        if (!pattern.test(email)) {
            setError("Please enter a valid email address");
            return;
        }
        setError("");
    };

    const emailEntered = useRef(false);
    useEffect(() => {
        // Do not validate when the page loads
        if (!emailEntered.current) {
            emailEntered.current = true;
            return;
        }

        validateEmail();
    }, [email]);

    const classes = useStyles();
    return (
        <div className={classes.paper}>
            <AppBar position="static" className={classes.header}>
                <Toolbar>
                    <Box className={classes.logoBox}>
                        <img height="36" src={logo} alt="logo" />
                    </Box>
                    <NfRedButton>Sign In</NfRedButton>
                </Toolbar>
            </AppBar>

            <Container maxWidth="sm" className={classes.centerForm}>
                <WhiteTypography paragraph variant="h3" align="center">
                    <Box fontWeight="Bold">
                        Unlimited movies, TV shows, and more.
                    </Box>
                </WhiteTypography>
                <WhiteTypography paragraph variant="h5" align="center">
                    Watch anywhere. Cancel anytime.
                </WhiteTypography>
                <form align="center">
                    <WhiteTypography gutterBottom variant="h5" align="center">
                        Ready to watch? Enter your email to create or restart
                        your membership.
                    </WhiteTypography>
                    <NfTextField
                        fullWidth
                        label="Email address"
                        type="email"
                        autoComplete="email"
                        inputProps={{ minLength: 5, maxLength: 50 }}
                        required
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        helperText={error}
                    />
                    <br />
                    <NfRedButton
                        type="submit"
                        endIcon={<ChevronRight />}
                        style={{ minHeight: "40px" }}
                    >
                        GET STARTED
                    </NfRedButton>
                </form>
            </Container>
        </div>
    );
}
