import React, {useEffect, useState} from 'react';
import './App.css';
import {
    Button,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow
} from '@mui/material';
import Axios from 'axios';
import AppService from'./App-Service';
import {PokemonModel} from './Pokemon.model';
import {PokemonDataModel} from './pokemonData.model';


function App() {
    let [rows, setRows] = useState([new PokemonDataModel('','')]);
    let pokemonModel=new PokemonModel('','','','','','','')
    let [isShown, setIsShown] = useState(false);
    let [pokemonChosen, setPokemonChosen] = useState(false);
    let [pokemonName, setPokemonName] = useState('');
    let [pokemon, setPokemon] = useState({pokemonModel});

    let appService:AppService;
    const searchPokemon = () => {
        Axios.get(`https://pokeapi.co/api/v2/pokemon/`+pokemonName).then(
            (res) => {
                pokemonModel= new PokemonModel(pokemonName
                    ,res.data.species.name
                    ,res.data.sprites.front_default
                    ,res.data.stats[0].base_stat
                    ,res.data.stats[1].base_stat
                    ,res.data.stats[2].base_stat
                    ,res.data.types[0].type.name);
                setPokemon({pokemonModel});
                setPokemonChosen(true);
            }
        )
    }
    const handleClick = (name:string) => {
        setPokemonName(name);
        searchPokemon();
        setIsShown(current => !current);
    };


    function fetchPokemons() {
        Axios.get(`https://pokeapi.co/api/v2/pokemon?limit=200`)
            .then((response) => {
                setRows(response.data.results)
            });
    }
    useEffect(() => {
        fetchPokemons()
    });
    return (
        <div>
            <TableContainer component={Paper}>
                <Table sx={{minWidth: 700}} aria-label="customized table">
                    <TableHead>
                        <TableRow className="header-row">
                            <TableCell className="header-cell" align="center">Name</TableCell>
                            <TableCell className="header-cell" align="center">Url</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {isShown && <div className="popup">
                            <div className="popup-inner">
                                <div className="displayedSection">
                                    {!pokemonChosen ? (<h1>Please choose a pokemon</h1>):(
                                       <div className="pokemon-infos-container">
                                           <h1>Pokemon Name: {pokemon.pokemonModel.name}</h1>
                                           <img src={pokemon.pokemonModel.img}/>
                                           <h3>Species: {pokemon.pokemonModel.species}</h3>
                                           <h3>Type: {pokemon.pokemonModel.type}</h3>
                                           <h4>Hp: {pokemon.pokemonModel.hp}</h4>
                                           <h4>Attack: {pokemon.pokemonModel.attacks}</h4>
                                           <h4>Defense: {pokemon.pokemonModel.defense}</h4>
                                       </div>
                                    )}
                                </div>
                                <div className="button-container">
                                    <Button variant="contained" className="button-close-popup"
                                            onClick={() => setIsShown(false)}>Close
                                    </Button>
                                </div>
                            </div>
                        </div>}
                        {rows.map((row) => (
                            <TableRow className="table-row" onClick={() => handleClick(row.name)}>
                                <TableCell align="center">{row?.name}</TableCell>
                                <TableCell align="center">{row?.url}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
}
export default App;
