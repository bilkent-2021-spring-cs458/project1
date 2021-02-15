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

const useStyles = makeStyles({
    checkbox: {
        "&:hover": {
            backgroundColor: "transparent !important",
        },
    },
});

export default function RegForm({ classes }) {
    const [shouldValidate, setShouldValidate] = useState(false);
    const submit = () => {
        setShouldValidate(true);
    };

    // TODO If already registered, show different content
    const myClasses = useStyles();
    return (
        <form>
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
                    fullWidth
                    label="Email"
                    required
                    className={classes.textField}
                    shouldValidate={shouldValidate}
                />
                <NfValidatedTextField
                    type="password"
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
                        />
                    }
                    label="Please do not email me Netflix special offers."
                />

                <NfRedButton
                    fullWidth
                    style={{ marginTop: "24px", minHeight: "48px" }}
                    onClick={submit}
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
