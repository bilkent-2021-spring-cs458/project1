import React, { useState } from "react";
import { Box, makeStyles, Typography, withStyles } from "@material-ui/core";
import { ChevronRight } from "@material-ui/icons";
import NfRedButton from "../components/NfRedButton";
import NfValidatedTextField from "../components/NfValidatedTextField";
import { useHistory } from "react-router-dom";
import { validateEmail } from "../validators";

const useStyles = makeStyles({
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

export default function LandingSignUpForm() {
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
        <form align="center" onSubmit={submit}>
            <WhiteTypography paragraph variant="h3" align="center">
                <Box fontWeight="Bold" component="span">
                    Unlimited movies, TV shows, and more.
                </Box>
            </WhiteTypography>
            <WhiteTypography paragraph variant="h5" align="center">
                Watch anywhere. Cancel anytime.
            </WhiteTypography>
            <WhiteTypography gutterBottom variant="h5" align="center">
                Ready to watch? Enter your email to create or restart your
                membership.
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
    );
}
