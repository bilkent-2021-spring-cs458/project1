import {
    Checkbox,
    Container,
    FormControlLabel,
    makeStyles,
    Typography,
} from "@material-ui/core";
import { CheckBoxOutlineBlank, CheckBoxOutlined } from "@material-ui/icons";
import React from "react";
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
    const myClasses = useStyles();

    // TODO If already registered, show different content

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
                />
                <NfValidatedTextField
                    type="password"
                    fullWidth
                    label="Add a password"
                    required
                    className={classes.textField}
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
                    type="submit"
                    fullWidth
                    style={{ marginTop: "24px", minHeight: "48px" }}
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
