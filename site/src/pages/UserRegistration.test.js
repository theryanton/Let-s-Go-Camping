import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import UserRegistration from './UserRegistration';
import axios from 'axios';
import { BrowserRouter } from 'react-router-dom';

jest.mock('axios');

const mockedNavigate = jest.fn();

jest.mock('react-router-dom', () => ({
  ...jest.requireActual('react-router-dom'),
  useNavigate: () => mockedNavigate,
}));

describe('UserRegistration', () => {
  beforeEach(() => {
    mockedNavigate.mockReset();
  });

  test('renders UserRegistration component', () => {
    render(
      <BrowserRouter>
        <UserRegistration />
      </BrowserRouter>
    );

    expect(screen.getByText(/user registration/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/username:/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/password:/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/confirm password:/i)).toBeInTheDocument();
  });

  test('allows entering data into the username and password fields', () => {
    render(
      <BrowserRouter>
        <UserRegistration />
      </BrowserRouter>
    );

    userEvent.type(screen.getByLabelText(/username:/i), 'newUser');
    userEvent.type(screen.getByLabelText(/password:/i), 'Password123');
    userEvent.type(screen.getByLabelText(/confirm password:/i), 'Password123');

    expect(screen.getByLabelText(/username:/i).value).toBe('newUser');
    expect(screen.getByLabelText(/password:/i).value).toBe('Password123');
    expect(screen.getByLabelText(/confirm password:/i).value).toBe('Password123');
  });

  test('submits the form and handles successful registration', async () => {
    axios.post.mockResolvedValueOnce({});

    render(
      <BrowserRouter>
        <UserRegistration />
      </BrowserRouter>
    );

    userEvent.type(screen.getByLabelText(/username:/i), 'newUser');
    userEvent.type(screen.getByLabelText(/password:/i), 'Password123');
    userEvent.type(screen.getByLabelText(/confirm password:/i), 'Password123');
    fireEvent.submit(screen.getByRole('button', { name: /register/i }));

    await screen.findByText(/register/i);

    expect(axios.post).toHaveBeenCalledWith('/register', {
      username: 'newUser',
      password: 'Password123',
    });
    expect(mockedNavigate).toHaveBeenCalledWith('/Login');
  });

  test('displays error when passwords do not match', async () => {
    render(
      <BrowserRouter>
        <UserRegistration />
      </BrowserRouter>
    );

    userEvent.type(screen.getByLabelText(/username:/i), 'newUser');
    userEvent.type(screen.getByLabelText(/password:/i), 'Password123');
    userEvent.type(screen.getByLabelText(/confirm password:/i), 'DifferentPassword');
    fireEvent.submit(screen.getByRole('button', { name: /register/i }));

    expect(await screen.findByText(/passwords do not match/i)).toBeInTheDocument();
  });

  test('displays modal when cancel button is clicked', () => {
    render(
      <BrowserRouter>
        <UserRegistration />
      </BrowserRouter>
    );

    userEvent.click(screen.getByRole('button', { name: /cancel/i }));

    expect(screen.getByText(/are you sure you want to exit registration/i)).toBeInTheDocument();
  });

  test('closes modal when continue button is clicked', () => {
    render(
      <BrowserRouter>
        <UserRegistration />
      </BrowserRouter>
    );

    userEvent.click(screen.getByRole('button', { name: /cancel/i }));
    userEvent.click(screen.getByRole('button', { name: /never mind!/i }));

    expect(screen.queryByText(/are you sure you want to exit registration/i)).not.toBeInTheDocument();
  });
});
