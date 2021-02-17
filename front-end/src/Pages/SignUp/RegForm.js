import {
    Checkbox,
    Container,
    FormControlLabel,
    makeStyles,
    Typography,
} from "@material-ui/core";
import { CheckBoxOutlineBlank, CheckBoxOutlined } from "@material-ui/icons";
import React, { useState } from "react";
import PropTypes from "prop-types";
import NfRedButton from "../../components/NfRedButton";
import NfValidatedTextField from "../../components/NfValidatedTextField";
import { validateEmail, validatePassword } from "../../validators";
import { useHistory } from "react-router-dom";
import { signup } from "../../service/Service";

const useStyles = makeStyles({
    checkbox: {
        "&:hover": {
            backgroundColor: "transparent !important",
        },
    },
});

export default function RegForm({ classes }) {
    const history = useHistory();
    const [shouldValidate, setShouldValidate] = useState(false);
    const submit = (e) => {
        e.preventDefault();
        const email = e.target.email.value;
        const password = e.target.password.value;
        const emailPreference = e.target.emailPreference.checked;
        const isEmailValid = validateEmail(email);
        const isPasswordValid = validatePassword(password);
        if (isEmailValid !== true || isPasswordValid !== true) {
            return;
        }

        console.log(email);
        console.log(password);
        console.log(emailPreference);
        signup({
            email,
            password,
            emailPreference,
            name: "todo",
            surname: "todo",
        }).then((response) => {
            console.log(response);
            history.push("/signup");
        });
    };

    // TODO If already registered, show different content
    const myClasses = useStyles();
    return (
        <form onSubmit={submit}>
            <Container maxWidth="xs">
                <span className={classes.stepIndicator}>
                    STEP <b>1</b> OF <b>3</b>
                </span>
                <Typography variant="h1" className={classes.stepTitle}>
                    Create a password to start your membership.
                </Typography>
                <div className={classes.contextRow}>
                    Just a few more steps and you&apos;re done!
                    <br />
                    We hate paperwork, too.
                </div>

                <NfValidatedTextField
                    type="email"
                    name="email"
                    fullWidth
                    label="Email"
                    required
                    className={classes.textField}
                    shouldValidate={shouldValidate}
                    initialValue={sessionStorage.getItem("email")}
                />
                <NfValidatedTextField
                    type="password"
                    name="password"
                    fullWidth
                    label="Add a password"
                    required
                    className={classes.textField}
                    shouldValidate={shouldValidate}
                />
                <FormControlLabel
                    control={
                        <Checkbox
                            disableRipple
                            icon={<CheckBoxOutlineBlank fontSize="large" />}
                            checkedIcon={
                                <CheckBoxOutlined
                                    fontSize="large"
                                    style={{ color: "#0071eb" }}
                                />
                            }
                            className={myClasses.checkbox}
                            name="emailPreference"
                        />
                    }
                    label="Please do not email me Netflix special offers."
                />

                <NfRedButton
                    type="submit"
                    fullWidth
                    style={{ marginTop: "24px", minHeight: "48px" }}
                    onClick={() => setShouldValidate(true)}
                >
                    Continue
                </NfRedButton>
            </Container>
        </form>
    );
}

RegForm.propTypes = {
    classes: PropTypes.object.isRequired,
};
