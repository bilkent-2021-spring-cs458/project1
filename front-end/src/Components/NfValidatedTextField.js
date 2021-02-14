import React, { useEffect, useRef, useState } from "react";
import PropTypes from "prop-types";
import { validateEmail, validatePassword } from "../validators";
import NfTextField from "./NfTextField";

export default function NfValidatedTextField(props) {
    let validator, minLength, maxLength;
    if (props.type == "email") {
        validator = validateEmail;
        minLength = 5;
        maxLength = 50;
    } else if (props.type == "password") {
        validator = validatePassword;
        minLength = 4;
        maxLength = 60;
    }
    const [value, setValue] = useState("");
    const [error, setError] = useState("");

    const valueEntered = useRef(false);
    useEffect(() => {
        // Do not validate when the page loads
        if (!valueEntered.current) {
            valueEntered.current = true;
            return;
        }

        const isValid = validator(value);
        if (isValid !== true) {
            setError(isValid);
        } else {
            setError("");
        }
    }, [value]);

    return (
        <NfTextField
            autoComplete={props.type}
            inputProps={{ minLength: minLength, maxLength: maxLength }}
            value={value}
            onChange={(e) => setValue(e.target.value)}
            error={error.length > 0}
            helperText={error}
            {...props}
        />
    );
}

NfValidatedTextField.propTypes = {
    type: PropTypes.oneOf(["email", "password"]).isRequired,
};
