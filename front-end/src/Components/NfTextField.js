import React from "react";
import { makeStyles, TextField } from "@material-ui/core";
import PropTypes from "prop-types";

const useStyles = makeStyles({
    root: {
        color: "white",
        "& .MuiInputBase-root": {
            backgroundColor: "white",
            border: "solid 1px #8c8c8c",
            borderRadius: "2px",
        },
        "& .MuiInputBase-root.Mui-focused": {
            borderColor: "#0071eb",
        },
        "& .MuiInputBase-root.Nf-validated": {
            // TODO
            borderColor: "#5fa53f",
        },
        "& .MuiInputBase-root.Mui-error": {
            borderBottom: "2px solid #ffa00a",
        },
        "& .MuiFormHelperText-root": {
            textAlign: "left",
            marginLeft: 0,
            paddingLeft: "3px",
        },
        "& .MuiFormLabel-root": {
            color: "#8c8c8c",
            fontSize: "16px",
        },
        "& .MuiFormLabel-asterisk": {
            display: "none",
        },
    },
});

export default function NfTextField(props) {
    const { className, ...otherProps } = props;
    const classes = useStyles();

    return (
        <TextField
            {...otherProps}
            variant="filled"
            className={(className ? className + " " : "") + classes.root}
            InputProps={{ disableUnderline: true }}
            FormHelperTextProps={{ className: classes.helperText }}
        />
    );
}

NfTextField.propTypes = {
    className: PropTypes.string,
};
