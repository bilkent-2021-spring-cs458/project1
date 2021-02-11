import {
    AppBar,
    Box,
    Container,
    Link,
    makeStyles,
    Toolbar,
    Typography,
} from "@material-ui/core";
import React from "react";
import NfRedButton from "../../components/NfRedButton";
import logo from "../../assets/logo.svg";
import devicesImg from "../../assets/Devices.png";

const useStyles = makeStyles({
    header: {
        boxShadow: "none",
        background: "transparent",
        padding: "0 25px",
        borderBottom: "1px solid #e6e6e6",
    },
    logoBox: {
        display: "flex",
        flexGrow: 1,
    },
    signInLink: {
        color: "#333",
        fontWeight: 700,
        fontSize: "19px",
        lineHeight: "90px",
    },
    centerForm: {
        marginTop: "120px",
        textAlign: "center",
    },
    devicesLogo: {
        maxWidth: "75%",
        paddingBottom: "20px",
    },
    stepTitle: {
        fontWeight: "700",
        fontSize: "23px",
        margin: "0 0 .4em",
    },
    contextBody: {
        fontSize: "17px",
    },
});

export default function Registration() {
    const classes = useStyles();
    return (
        <>
            <AppBar position="static" className={classes.header}>
                <Toolbar style={{ padding: 0 }}>
                    <Box className={classes.logoBox}>
                        <img height="45" src={logo} alt="logo" />
                    </Box>
                    <Link
                        href="#"
                        color="primary"
                        className={classes.signInLink}
                    >
                        Sign In
                    </Link>
                </Toolbar>
            </AppBar>

            <Container maxWidth="xs" className={classes.centerForm}>
                <img src={devicesImg} className={classes.devicesLogo} />
                <br />
                <span style={{ fontSize: "13px" }}>
                    STEP <b>1</b> OF <b>3</b>
                </span>
                <Typography variant="h1" className={classes.stepTitle}>
                    Finish setting up your account.
                </Typography>
                <div className={classes.contextBody}>
                    Netflix is personalized for you. Create a password to watch
                    Netflix on any device at any time.
                </div>
                <NfRedButton
                    fullWidth
                    style={{ marginTop: "24px", minHeight: "48px" }}
                >
                    Continue
                </NfRedButton>
            </Container>
        </>
    );
}
