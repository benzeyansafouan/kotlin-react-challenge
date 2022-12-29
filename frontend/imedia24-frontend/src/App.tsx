import React, {useEffect, useState} from 'react';
import './App.css';
import {
    Paper,
    styled,
    Table,
    TableBody,
    TableCell,
    tableCellClasses,
    TableContainer,
    TableHead,
    TableRow
} from '@mui/material';
import Axios from 'axios';


function App() {

    function createData(
        img: string,
        name: string,
        hp: string,
        attack: string,
        defense: string,
    ) {
        return {img, name, hp, attack, defense};
    }

    const StyledTableCell = styled(TableCell)(({theme}) => ({
        [`&.${tableCellClasses.head}`]: {
            backgroundColor: theme.palette.common.black,
            color: theme.palette.common.white,
        },
        [`&.${tableCellClasses.body}`]: {
            fontSize: 14,
        },
    }));

    const StyledTableRow = styled(TableRow)(({theme}) => ({
        '&:nth-of-type(odd)': {
            backgroundColor: theme.palette.action.hover,
        },
        // hide last border
        '&:last-child td, &:last-child th': {
            border: 0,
        },
    }));


    function fetchPokemon() {
        Axios.get(`https://pokeapi.co/api/v2/pokemon/charmander`)
        // fetch('https://pokeapi.co/api/v2/pokemon/charmander')
            .then((response) => {
                console.log(response)
                // createData(
                   //  response.data.species.name,
                   // response.data.sprites.front_default,
                   //  "",
                   //  "",
                   // ""
                // )
            })
    }
    useEffect(() => {
        fetchPokemon()
    });
    const rows = [
        createData('Frozen yoghurt', '159', '6.0', '24', '4.0'),
        createData('Ice cream sandwich', '237', '9.0', '37', '4.3'),
        createData('Eclair', '262', '16.0', '24', '6.0'),
        createData('Cupcake', '305', '3.7', '67', '4.3'),
        createData('Gingerbread', '356', '16.0', '49', '3.9'),
    ];



    return (
        <div>
            <TableContainer component={Paper}>
                <Table sx={{minWidth: 700}} aria-label="customized table">
                    <TableHead>
                        <TableRow>
                            <StyledTableCell>Pokemon Image</StyledTableCell>
                            <StyledTableCell align="right">Name</StyledTableCell>
                            <StyledTableCell align="right">Hp</StyledTableCell>
                            <StyledTableCell align="right">attack</StyledTableCell>
                            <StyledTableCell align="right">defense</StyledTableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {rows.map((row) => (
                            <StyledTableRow key={row.name}>
                                <StyledTableCell component="th" scope="row">
                                    {row.img}
                                </StyledTableCell>
                                <StyledTableCell align="right">{row.name}</StyledTableCell>
                                <StyledTableCell align="right">{row.hp}</StyledTableCell>
                                <StyledTableCell align="right">{row.attack}</StyledTableCell>
                                <StyledTableCell align="right">{row.defense}</StyledTableCell>
                            </StyledTableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
}

export default App;
