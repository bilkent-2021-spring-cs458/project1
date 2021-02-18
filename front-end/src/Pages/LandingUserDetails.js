import { Box, makeStyles, Typography } from "@material-ui/core";
import React, { useEffect, useState } from "react";
import { getUserDetails } from "../service/Service";

const useStyles = makeStyles({
    background: {
        background: "rgba(0, 0, 0, 0.75)",
        color: "white",
        borderRadius: "30px",
        padding: "35px",
    },
});

export default function LandingUserDetails() {
    const [userDetails, setUserDetails] = useState({});
    useEffect(() => {
        getUserDetails()
            .then((response) => {
                setUserDetails(response.data);
            })
            .catch(() => {
                alert(
                    "An error occured while trying to get your user details."
                );
            });
    }, []);

    const classes = useStyles();
    return (
        <Typography paragraph variant="h4" className={classes.background}>
            <Box fontWeight="Bold" component="span">
                You are currently signed in.
                <br />
                Your user details:
                <br />
                <br />
                Email:&nbsp;
                {userDetails ? userDetails.email : ""}
                <br />
                Name:&nbsp;
                {userDetails ? userDetails.name : ""}
                <br />
                Surname:&nbsp;
                {userDetails ? userDetails.surname : ""}
                <br />
                Payment Plan:&nbsp;
                {userDetails ? userDetails.plan : ""}
            </Box>
        </Typography>
    );
}
