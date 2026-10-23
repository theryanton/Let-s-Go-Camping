import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import Login from './Login';
import axios from 'axios';
import { BrowserRouter } from 'react-router-dom';


jest.mock('axios');

const mockedNavigate = jest.fn();

jest.mock('react-router-dom', () => ({
  ...jest.requireActual('react-router-dom'),
  useNavigate: () => mockedNavigate,
}));

describe('Login', () => {
  beforeEach(() => {
    mockedNavigate.mockReset();
  });

  test('renders Login component', () => {
    render(
      <BrowserRouter>
        <Login />
      </BrowserRouter>
    );

    expect(screen.getByText(/login/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/username:/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/password:/i)).toBeInTheDocument();
  });

  test('allows the user to enter username and password', () => {
    render(
      <BrowserRouter>
        <Login />
      </BrowserRouter>
    );

    userEvent.type(screen.getByLabelText(/username:/i), 'testUser');
    userEvent.type(screen.getByLabelText(/password:/i), 'password');

    expect(screen.getByLabelText(/username:/i).value).toBe('testUser');
    expect(screen.getByLabelText(/password:/i).value).toBe('password');
  });

  test('handles form submission and navigates to SearchPage on success', async () => {
    axios.post.mockResolvedValue({ data: { message: 'Login successful', user_id: 1 } });

    render(
      <BrowserRouter>
        <Login />
      </BrowserRouter>
    );

    userEvent.type(screen.getByLabelText(/username:/i), 'testUser');
    userEvent.type(screen.getByLabelText(/password:/i), 'password');
    fireEvent.submit(screen.getByText(/login/i));

    await waitFor(() => expect(axios.post).toHaveBeenCalledWith('/login', {
      username: 'testUser',
      password: 'password'
    }));

    await waitFor(() => expect(mockedNavigate).toHaveBeenCalledWith('/SearchPage'));
  });

  test('displays error message on failed login', async () => {
    const errorMessage = 'Invalid credentials';
    axios.post.mockRejectedValue({ response: { status: 401, data: { message: errorMessage } } });

    render(
      <BrowserRouter>
        <Login />
      </BrowserRouter>
    );

    userEvent.type(screen.getByLabelText(/username:/i), 'wrongUser');
    userEvent.type(screen.getByLabelText(/password:/i), 'wrongPassword');
    fireEvent.submit(screen.getByText(/login/i));

    await waitFor(() => expect(axios.post).toHaveBeenCalled());
    await waitFor(() => expect(screen.getByText(errorMessage)).toBeInTheDocument());
  });

  test('navigates to the registration page when register button is clicked', () => {
    render(
      <BrowserRouter>
        <Login />
      </BrowserRouter>
    );

    userEvent.click(screen.getByText(/register/i));

    expect(mockedNavigate).toHaveBeenCalledWith('/UserRegistration');
  });
});

