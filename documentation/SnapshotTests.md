# Snapshot Tests

The Pocket League app leverages [Paparazzi](https://github.com/cashapp/paparazzi) for snapshot testing. Most of this happens inside the [android design system module](../android/design-system).

## Verifying Paparazzi Tests

To verify that your changes haven't broken any Paparazzi tests, you can run the following command:

```bash
./gradlew verifyPaparazziDebug
```

## Updating Snapshots

If you have changed views, and need to generate new snapshots for Paparazzi to compare against, you can run the following command:

```bash
./gradlew recordPaparazziDebug
```

After this, you can add the generated screenshot files to git and push them up with your changes. 
