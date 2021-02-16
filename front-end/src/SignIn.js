import {
    AppBar,
    Box,
    Button,
    Container,
    makeStyles,
    withStyles,
    TextField,
    Typography,
    Checkbox,
    FormControlLabel,
} from "@material-ui/core";
import { CheckBoxOutlineBlank, CheckBoxOutlined } from "@material-ui/icons";
import React from "react";
import netflix_logo from "./assets/logo.svg";
import fb_logo from "./fb_logo.png";
import backgroundImg from "./signin_background.jpg";

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
    centerForm: { padding: "150px 50px 0" },
    textField: {
        backgroundColor: "#333",
        marginBottom: "20px",
    },
    rectangle: {
        display: "inline-block",
        width: "450px",
        height: "600px",
        background: "rgba(0, 0, 0, 0.75)",
        position: "absolute",
        top: "10%",
    },
    checkbox: {
        align: "left",
        "&:hover": {
            backgroundColor: "transparent !important",
        },
    },
    remember: {
        fontSize: "13px",
        color: "#b3b3b3",
    },
    help: {
        textDecoration: "none",
        "&:hover": {
            textDecoration: "underline",
        },
        fontSize: "13px",
        color: "#b3b3b3",
    },
    facebook: {
        textDecoration: "none",
        fontSize: "13px",
        fontWeight: "500",
        color: "#737373",
    },
});

const WhiteTypography = withStyles({
    root: {
        marginTop: "40px",
        color: "white",
    },
})(Typography);

const NfRedButton = withStyles({
    root: {
        textTransform: "none",
        background: "#e50914",
        color: "white",
        borderRadius: 3,
        marginTop: "10px",
        height: "50px",
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

export default function SignIn() {
    const classes = useStyles();
    return (
        <>
            <div className={classes.paper}>
                <AppBar position="static" className={classes.header}>
                    <Box className={classes.logoBox}>
                        <img height="44" src={netflix_logo} alt="logo" />
                    </Box>
                </AppBar>

                <Container maxWidth="xs" className={classes.centerForm}>
                    <Container className={classes.rectangle}>
                        <form align="center">
                            <WhiteTypography
                                paragraph
                                variant="h4"
                                align="left"
                            >
                                <Box fontWeight="Bold">Sign In</Box>
                            </WhiteTypography>
                            <TextField
                                fullWidth
                                variant="filled"
                                label="Email or phone number"
                                background="#6c6d73"
                                className={classes.textField}
                                InputProps={{ disableUnderline: true }}
                            />
                            <TextField
                                fullWidth
                                variant="filled"
                                label="Password"
                                className={classes.textField}
                                InputProps={{ disableUnderline: true }}
                            />
                            <NfRedButton fullWidth type="submit" disableRipple>
                                Sign In
                            </NfRedButton>
                        </form>
                        <FormControlLabel
                            control={
                                <Checkbox
                                    disableRipple
                                    icon={
                                        <CheckBoxOutlineBlank
                                            fontSize="small"
                                            style={{ color: "white" }}
                                        />
                                    }
                                    checkedIcon={
                                        <CheckBoxOutlined
                                            fontSize="small"
                                            style={{ color: "white" }}
                                        />
                                    }
                                    className={classes.checkbox}
                                />
                            }
                            className={classes.remember}
                            label="Remember me"
                        />

                        <form align="right">
                            <a href="#" className={classes.help}>
                                Need Help?
                            </a>
                        </form>

                        <AppBar position="static" className={classes.header}>
                            <Box className={classes.logoBox}>
                                <img height="20" src={fb_logo} alt="logo" />
                            </Box>

                            <a href="#" className={classes.facebook}>
                                Login with facebook
                            </a>
                        </AppBar>

                        <Container position="static" className={classes.header}>
                            <WhiteTypography
                                className={classes.WhiteTypography}
                            >
                                New to Netflix?
                            </WhiteTypography>

                            <a href="#" className={classes.help}>
                                Sign up now
                            </a>

                            <WhiteTypography
                                className={classes.WhiteTypography}
                            >
                                This page is protected by Google reCAPTCHA to
                                ensure you are not a bsot.
                            </WhiteTypography>

                            <a href="#" className={classes.help}>
                                Learn more
                            </a>
                        </Container>
                    </Container>
                </Container>
            </div>
        </>
    );
}
