import React, {useState} from 'react';
import {fireEvent, render, screen} from '@testing-library/react';
import App from './App';
import {Table, TableBody, TableCell, TableRow} from '@mui/material';

test('renders learn react link', () => {
    render(<App/>);
    const linkElement = screen.getByText(/learn react/i);
    expect(linkElement).toBeInTheDocument();
});

test('MyButton should render with the correct text and handle clicks correctly', () => {
    const handleClick = jest.fn();
    function createData(
        name: string,
        url: string
    ) {
        return {name, url};
    }
    let [rows, setRows] = useState([createData('bulbasaur', '')]);
    const {getByText} = render(<Table>
    <TableBody>
        <TableRow>
            {rows.map((row) => (
                <TableRow onClick={() => handleClick(row.name)}>
                    <TableCell align="center">{row?.name}</TableCell>
                    <TableCell align="center">{row?.url}</TableCell>
                </TableRow>
            ))}
        </TableRow>
    </TableBody>
    </Table>);

    const row = getByText('bulbasaur');
    expect(row).toBeInTheDocument();
    fireEvent.click(row);
    expect(handleClick).toHaveBeenCalledTimes(1);
});

// describe('My Table', () => {
//     it('should allow a user to click on a row and view details', () => {
//         cy.visit('http://localhost:3000');
//         cy.get('TableBody TableRow').first().click();
//         cy.get('TableBody TableRow').first().should('contain', 'bulbasaur');
//     });
// });

