import {
    AppBar,
    Box,
    Button,
    Container,
    makeStyles,
    withStyles,
    TextField,
    Toolbar,
    Typography,
} from "@material-ui/core";
import { ChevronRight } from "@material-ui/icons";
import React from "react";
import logo from "./logo.svg";
import backgroundImg from "./background.jpg";

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
        color: "white",
        backgroundColor: "white",
    },
});

const WhiteTypography = withStyles({
    root: {
        color: "white",
    },
})(Typography);

const NfRedButton = withStyles({
    root: {
        textTransform: "none",
        background: "#e50914",
        color: "white",
        borderRadius: 3,
        paddingLeft: "1em",
        paddingRight: "1em",
        margin: 4,
        "&:hover": {
            background: "#f40612",
        },
        "&:active": {
            background: "#bb0a12",
        },
        "&:focus": {
            outline: "none",
            border: "0.085em solid black",
            boxShadow: "0 0 0 0.085em white",
        },
    },
})(Button);

export default function SignUp() {
    const classes = useStyles();
    return (
        <>
            <div className={classes.paper}>
                <AppBar position="static" className={classes.header}>
                    <Toolbar>
                        <Box className={classes.logoBox}>
                            <img height="36" src={logo} alt="logo" />
                        </Box>
                        <NfRedButton disableRipple>Sign In</NfRedButton>
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
                        <WhiteTypography
                            gutterBottom
                            variant="h5"
                            align="center"
                        >
                            Ready to watch? Enter your email to create or
                            restart your membership.
                        </WhiteTypography>
                        <TextField
                            fullWidth
                            variant="filled"
                            label="Email address"
                            className={classes.textField}
                            InputProps={{ disableUnderline: true }}
                        />
                        <br />
                        <NfRedButton
                            type="submit"
                            endIcon={<ChevronRight />}
                            disableRipple
                        >
                            GET STARTED
                        </NfRedButton>
                    </form>
                </Container>
            </div>
        </>
    );
}
