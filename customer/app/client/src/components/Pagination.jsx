import styled from "styled-components";

export function Pagination({ total, limit, page, setPage }) {
  const numPages = Math.ceil(total / limit);

  return (
    <>
      <Nav>
        <Button onClick={() => setPage(page - 1)} disabled={page === 1}>
          &lt;
        </Button>
        {Array(numPages)
          .fill()
          .map((_, i) => (
            <Button
              key={i + 1}
              onClick={() => setPage(i + 1)}
              aria-current={page === i + 1 ? "page" : null}
            >
              {i + 1}
            </Button>
          ))}
        <Button onClick={() => setPage(page + 1)} disabled={page === numPages}>
          &gt;
        </Button>
      </Nav>
    </>
  );
}

const Nav = styled.nav`
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 0.5%;
  margin-top: 5%;
`;

const Button = styled.button`
  border: none;
  border-radius: 0.4vh;
  padding: 1.2% 1.5% 1.2% 1.5%;
  margin: 0;
  background: lightgrey;
  color: white;
  font-size: 1.2vh;

  &:hover {
    background: tomato;
    cursor: pointer;
    transform: translateY(-2px);
  }

  &[disabled] {
    background: lightgrey;
    cursor: revert;
    transform: revert;
  }

  &[aria-current] {
    background: #FFB6A3;
    font-weight: bold;
    cursor: revert;
    transform: revert;
  }
`;

export default Pagination;
