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
import React from "react";

import logo from "../assets/logo.svg";
import backgroundImg from "../assets/background.jpg";
import NfRedButton from "../components/NfRedButton";
import NfValidatedTextField from "../components/NfValidatedTextField";

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
                    <Box fontWeight="Bold" component="span">
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
                    <NfValidatedTextField
                        type="email"
                        fullWidth
                        label="Email address"
                        required
                        className={classes.textField}
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
