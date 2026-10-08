public class KitMedico extends Item {
    private static final int CURA = 40;

    public KitMedico() {
        super("Kit Médico", "Recupera " + CURA + " de vida");
    }

    @Override
    public boolean usar(Sobrevivente usuario) {
        if (usuario.getVida() == usuario.getVidaMaxima()) {
            System.out.println("Sua vida já está cheia.");
            return false;
        }
        int curado = usuario.curar(CURA);
        System.out.println(usuario.getNome() + " usou um Kit Médico e recuperou " + curado + " de vida.");
        return true;
    }
}
